package com.example.orderhw.Service;

import com.example.orderhw.DTO.Create.PaymentDto;
import com.example.orderhw.DTO.ToBody.OrderProductOutDto;
import com.example.orderhw.DTO.ToBody.PaymentOutDto;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Repository.OrderRepository;
import com.example.orderhw.Repository.PaymentRepository;
import com.example.orderhw.Status.OrderStatus;
import com.example.orderhw.Status.PaymentLogStatus;
import com.example.orderhw.Status.PaymentLogTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    public Long addPayment(PaymentDto paymentDto) throws Exception {
        Order order= orderRepository
                .findById(paymentDto.orderId())
                .orElseThrow(() -> new RuntimeException());
        List<PaymentLog> paymentLogList = paymentRepository.findAll();
        List<Long> paymentOrderId = new ArrayList<>();
        for(PaymentLog paymentLog :paymentLogList){
            paymentOrderId.add(paymentLog.getOrderIdKey());
        }

        if(paymentOrderId.contains(paymentDto.orderId())){
            throw new RuntimeException();
        }else{
            PaymentLog paymentLog = paymentDto.toEntity(order);
            paymentRepository.save(paymentLog);
            return paymentLog.getId();

        }

    }

    public List<PaymentOutDto> getPaymentPage(Pageable pageable) {
        pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        List<PaymentLog> all = paymentRepository.findAllOrderBypaymentTimeDesc(pageable).getContent();
        List<PaymentOutDto> paymentOutDtoList= new ArrayList<>();
        for(PaymentLog paymentLog: all){
            PaymentOutDto paymentOutDto = PaymentOutDto.builder()
                    .id(paymentLog.getId())
                    .orderId(paymentLog.getOrderIdKey())
                    .paymentTool(paymentLog.getPaymentTool())
                    .paymentPriceActual(paymentLog.getPaymentPriceActual())
                    .paymentStatus(paymentLog.getPaymentStatus())
                    .paymentTime(paymentLog.getPaymentTime())
                    .build();
            paymentOutDtoList.add(paymentOutDto);
        }
        Page<PaymentOutDto> paymentAll= new PageImpl<>(paymentOutDtoList, pageable, paymentOutDtoList.size());
        List<PaymentOutDto> paymentPage = paymentAll.getContent();
        return paymentPage;
    }

    public PaymentOutDto findOnePaymentLog(Long paymentId) throws Exception{
        PaymentLog paymentLog= paymentRepository
                .findById(paymentId)
                .orElseThrow(()-> new RuntimeException());
        PaymentOutDto paymentOutDto = PaymentOutDto.builder()
                .id(paymentLog.getId())
                .orderId(paymentLog.getOrderIdKey())
                .paymentTool(paymentLog.getPaymentTool())
                .paymentPriceActual(paymentLog.getPaymentPriceActual())
                .paymentStatus(paymentLog.getPaymentStatus())
                .paymentTime(paymentLog.getPaymentTime())
                .build();
        return paymentOutDto;
    }
}
