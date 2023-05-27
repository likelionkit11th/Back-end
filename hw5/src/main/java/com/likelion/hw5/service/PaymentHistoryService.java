package com.likelion.hw5.service;

import com.likelion.hw5.domain.PaymentHistory;
import com.likelion.hw5.repository.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;


    public List<PaymentHistory> getPaymentHistoryList(Pageable pageable){

        return paymentHistoryRepository.findAll(pageable).toList();

    }

}
