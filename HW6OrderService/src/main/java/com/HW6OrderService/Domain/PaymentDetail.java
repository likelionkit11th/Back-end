package com.HW6OrderService.Domain;

import com.HW6OrderService.Domain.Status.PaymentStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class PaymentDetail {
    @Id  @GeneratedValue
    @Column(name = "payment_id")
    private Long id;
    private String method;
    private String paymentPrice;

    @CreatedDate
    private LocalDate paymentDateTime;

    //결제 상태
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
    //주문  1대1 매핑 추가
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
