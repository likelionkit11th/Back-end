package com.example.orderhw.Domain;

import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "order_member_id")
    private String orderMemberName;
    @Column(name = "order_price")
    private Long orderPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
//    @Builder.Default()
    private OrderStatus orderStatus = OrderStatus.REQUESTED;
    @CreatedDate
    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Builder
    public Order(String orderMemberName, Long orderPrice, OrderStatus orderStatus, Member member) {
        this.orderMemberName = orderMemberName;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.member = member;
    }

    public void update(String orderMemberName, Long orderPrice, OrderStatus orderStatus, Member member) {
        this.orderMemberName = orderMemberName;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.member = member;

    }

    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }
}
