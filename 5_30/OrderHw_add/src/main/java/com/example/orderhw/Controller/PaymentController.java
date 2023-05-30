package com.example.orderhw.Controller;

import com.example.orderhw.DTO.MemberDto;
import com.example.orderhw.DTO.PaymentDto;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Service.MemberService;
import com.example.orderhw.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/Payment/")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("payments")
    public ResponseEntity<Long> addPayment(@RequestBody PaymentDto paymentDto) {
        Long paymentId = paymentService.addPayment(paymentDto);
        return ResponseEntity.created(URI.create("payments/" + paymentId)).body(paymentId);

    }

    @GetMapping("payments/{paymentId}")
    public ResponseEntity<PaymentLog> findOnePayment(@PathVariable Long paymentId) throws Exception {
        PaymentLog paymentLog = paymentService.findOnePaymentLog(paymentId);
        return ResponseEntity.ok().body(paymentLog);
    }

    @GetMapping("paymentlist")
    public ResponseEntity<List<PaymentLog>> findAllPayments(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<PaymentLog> paymentLogs = paymentService.getPaymentPage(pageable);
        return ResponseEntity.ok().body(paymentLogs);
    }
}
