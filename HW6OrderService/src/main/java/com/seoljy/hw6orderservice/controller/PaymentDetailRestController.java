package com.seoljy.hw6orderservice.controller;

import com.seoljy.hw6orderservice.domain.Order;
import com.seoljy.hw6orderservice.domain.PaymentDetail;
import com.seoljy.hw6orderservice.service.OrderService;
import com.seoljy.hw6orderservice.service.PaymentDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentDetailRestController {
    private final PaymentDetailService paymentDetailService;

    @GetMapping("/all")
    public ResponseEntity listPayment( @PageableDefault Pageable pageable ) {
        List<PaymentDetail> paymentDetails = paymentDetailService.findAll(pageable);
        return ResponseEntity.ok().body(paymentDetails);
    }
}
