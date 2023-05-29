package com.seoljy.hw6orderservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="orders_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentDetailMethod paymentDetailMethod;

    private Long actualPrice;

    @Enumerated(EnumType.STRING)
    private PaymentDetailStatus paymentDetailStatus;

    @CreatedDate
    private LocalDateTime createdDate;
}