package mutsa.assignment5.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Getter
@Table(name="payment_detail")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_detail_id")
    private Long paymentDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orders;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Long actualPaymentAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name="payment_date")
    @CreatedDate
    private LocalDate paymentDate;
}
