package com.example.orderhw.Service;

import com.example.orderhw.DTO.MemberDto;
import com.example.orderhw.DTO.PaymentDto;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Repository.MemberRepository;
import com.example.orderhw.Repository.OrderRepository;
import com.example.orderhw.Repository.PaymentRepository;
import com.example.orderhw.Status.OrderStatus;
import com.example.orderhw.Status.PaymentLogTool;
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
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public Long addPayment(PaymentDto paymentDto) {
        PaymentLog paymentLog = paymentDto.toEntity();
        paymentRepository.save(paymentLog);
        paymentDto.order().setOrderStatus(OrderStatus.PAID);
        return paymentLog.getId();
    }

    public List<PaymentLog> getPaymentPage(Pageable pageable) {
        Page<PaymentLog> all = paymentRepository.findAll(pageable);
        List<PaymentLog> paymentpage= all.getContent();
        return paymentpage;
    }

    public PaymentLog findOnePaymentLog(Long paymentId) throws Exception{
        return paymentRepository
                .findById(paymentId)
                .orElseThrow(()-> new RuntimeException());
    }
}
