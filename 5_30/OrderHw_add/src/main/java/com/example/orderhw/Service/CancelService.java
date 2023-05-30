package com.example.orderhw.Service;

import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Repository.CancelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CancelService {
    private final CancelRepository cancelRepository;
    public List<CancelLog> getCancelPage(Pageable pageable) {
        Page<CancelLog> all = cancelRepository.findAll(pageable);
        List<CancelLog> cancelLogPage = all.getContent();
        return cancelLogPage;
    }

    public CancelLog findOneCancelLog(Long cancelId) throws Exception{
        return cancelRepository
                .findById(cancelId)
                .orElseThrow(()-> new RuntimeException());
    }
}
