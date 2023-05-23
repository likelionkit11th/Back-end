package com.example.orderhw.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name="payments")
@Entity
public class PaymentLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order orderId;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_tool")
    private PaymentLogTool paymentTool;

    @Column(name="payment_price_actual")
    private Long paymentPriceActual;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_status")
    private PaymentLogStatus paymentStatus;

    @CreatedDate
    @Column(name="payment_time")
    private LocalDateTime paymentTime;
}
