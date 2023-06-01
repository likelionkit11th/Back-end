package com.example.orderhw.Domain;

import com.example.orderhw.Status.CancelLogStatus;
import com.example.orderhw.Status.PaymentLogStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor(access =AccessLevel.PROTECTED)
@Entity
@Table(name = "cancels")
@NoArgsConstructor
public class CancelLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cancel_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderId;
    @Column(name="cancel_price")
    private Long cancelPrice;

    @Enumerated(EnumType.STRING)
    @Column(name="cancel_status")
    private CancelLogStatus cancelstatus;

    @CreatedDate
    @Column(name="cancel_time")
    private LocalDateTime cancelTime;

    @Builder
    public CancelLog(Order order, Long cancelPrice){
        setOrder(order);
        this.cancelPrice=cancelPrice;
        this.cancelstatus=CancelLogStatus.REQUESTED;
        this.cancelTime=LocalDateTime.now();
    }

    public void setOrder(Order order) {
        this.orderId = order;
        order.setOrderCancel(this);
    }
    public Long getOrderIdKey(){
        return this.orderId.getId();
    }
    public void setProductStatus(CancelLogStatus cancelLogStatus){
        this.cancelstatus=cancelstatus;
    }

}
