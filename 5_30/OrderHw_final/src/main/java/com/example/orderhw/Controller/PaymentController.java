package com.example.orderhw.Controller;

import com.example.orderhw.DTO.Create.PaymentDto;
import com.example.orderhw.DTO.ToBody.PaymentOutDto;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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

    @PostMapping("payments")  // 결제 추가
    public ResponseEntity<Long> addPayment(@RequestBody PaymentDto paymentDto) throws Exception {
        Long paymentId = paymentService.addPayment(paymentDto);
        return ResponseEntity.created(URI.create("payments/" + paymentId)).body(paymentId);

    }

    @GetMapping("payments/{paymentId}")  //단건 조회
    public ResponseEntity<PaymentOutDto> findOnePayment(@PathVariable Long paymentId) throws Exception {
        PaymentOutDto paymentOutDto= paymentService.findOnePaymentLog(paymentId);
        return ResponseEntity.ok().body(paymentOutDto);
    }

    @GetMapping("paymentlist")  // 전체 페이지
    public ResponseEntity<List<PaymentOutDto>> findAllPayments(
            @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        List<PaymentOutDto> paymentLogs = paymentService.getPaymentPage(pageable);
        return ResponseEntity.ok().body(paymentLogs);
    }
}
