package com.likelion.hw5.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistory {



    @Id @GeneratedValue
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // 결제 수단

    private Integer paymentAmount; // 결제 금액

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paidAt; // 결제 일시

    public static enum PaymentMethod{
        CARD, KAKAO_PAY, NAVER_PAY
    }

    public static enum PaymentStatus{
        COMPLETED, // 성공
        FAILED_INSUFFICIENT_FUNDS, // 잔액 부족
        FAILED_ACCOUNT_SUSPENSION // 계좌 정지
    }

}
