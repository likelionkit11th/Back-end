package likelion.assignment6.entity;

import jakarta.persistence.*;
import likelion.assignment6.dto.PaymentDetailDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="payment_detail")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    private LocalDateTime paymentDate;


    public static PaymentDetail newPaymentDetail(Order order, Long actualPaymentAmount) {
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setOrders(order);
        paymentDetail.setPaymentMethod(PaymentMethod.Cash);
        paymentDetail.setActualPaymentAmount(actualPaymentAmount);
        paymentDetail.setOrderStatus(OrderStatus.ORDERED);
        return paymentDetail;
    }

    public static PaymentDetailDto toDto(PaymentDetail paymentDetail) {
        PaymentDetailDto paymentDto = new PaymentDetailDto(paymentDetail.getActualPaymentAmount(), paymentDetail.getPaymentDate());
        return paymentDto;
    }



}
