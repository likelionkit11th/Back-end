package com.example.orderhw.Domain;

import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.PaymentLogStatus;
import com.example.orderhw.Status.PaymentLogTool;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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
//    @Builder.Default()
    @Column(name="payment_status")
    private PaymentLogStatus paymentStatus;

    @CreatedDate
    @Column(name="payment_time")
    private LocalDateTime paymentTime;

    @Builder
    public PaymentLog(Order order ,PaymentLogTool paymentTool, Long paymentPriceActual){
        this.orderId=order;
        this.paymentTool=paymentTool;
        this.paymentPriceActual=paymentPriceActual;
        this.paymentStatus=paymentStatus;
        this.paymentStatus=PaymentLogStatus.REQUESTED;
        this.paymentTime=LocalDateTime.now();
    }
    public Long getOrderIdKey(){
        return this.orderId.getId();
    }
    public void setProductStatus(PaymentLogStatus paymentStatus){
        this.paymentStatus=paymentStatus;
    }
}
