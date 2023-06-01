package com.example.orderhw.Service;

import com.example.orderhw.DTO.ToBody.CancelOutDto;
import com.example.orderhw.DTO.ToBody.PaymentOutDto;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Repository.CancelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CancelService {
    private final CancelRepository cancelRepository;
    public List<CancelOutDto> getCancelPage(Pageable pageable) {
        pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC,"id"));
        Page<CancelLog> all = cancelRepository.findAll(pageable);
        List<CancelOutDto> cancelOutDtoList= new ArrayList<>();
        for(CancelLog cancelLog: all){
            CancelOutDto cancelOutDto= CancelOutDto.builder()
                    .id(cancelLog.getId())
                    .orderId(cancelLog.getOrderIdKey())
                    .cancelPrice(cancelLog.getCancelPrice())
                    .cancelLogStatus(cancelLog.getCancelstatus())
                    .cancelTime(cancelLog.getCancelTime())
                    .build();
            cancelOutDtoList.add(cancelOutDto);
        }
        Page<CancelOutDto> cancelAll= new PageImpl<>(cancelOutDtoList, pageable, cancelOutDtoList.size());
        List<CancelOutDto> cancelPage = cancelAll.getContent();
        return cancelPage;
    }

    public CancelOutDto findOneCancelLog(Long cancelId) throws Exception{
        CancelLog cancelLog=cancelRepository
                .findById(cancelId)
                .orElseThrow(()-> new RuntimeException());
        CancelOutDto cancelOutDto= CancelOutDto.builder()
                .id(cancelLog.getId())
                .orderId(cancelLog.getOrderIdKey())
                .cancelPrice(cancelLog.getCancelPrice())
                .cancelLogStatus(cancelLog.getCancelstatus())
                .cancelTime(cancelLog.getCancelTime())
                .build();
        return cancelOutDto;
    }
}
