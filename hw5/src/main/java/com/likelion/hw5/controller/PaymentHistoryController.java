package com.likelion.hw5.controller;

import com.likelion.hw5.domain.PaymentHistory;
import com.likelion.hw5.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payments/")
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    @GetMapping("list")
    public List<PaymentHistory> getPaymentList(
            @PageableDefault(sort = {"paidAt"},direction = Sort.Direction.DESC) Pageable pageable){

        return paymentHistoryService.getPaymentHistoryList(pageable);
    }

}
