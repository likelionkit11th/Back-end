package likelion.assignment6.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Getter
@Table(name="cancel_detail")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CancellationDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancel_id")
    private Long cancelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orders;

    private Long orderPrice;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;

    @Column(name="cancel_date")
    @CreatedDate
    private LocalDate canceledDate;




}
