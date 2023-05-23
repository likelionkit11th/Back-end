package com.example.orderhw.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name="order_member_id")
    private String orderMemberName;
    @Column(name="order_price")
    private Long orderPrice;
    @Enumerated(EnumType.STRING)
    @Column(name="order_status")
    private OrderStatus orderStatus;
    @CreatedDate
    @Column(name="order_time")
    private LocalDateTime orderTime;

    @Builder
    public Order(String orderMemberName, Long orderPrice){
        this.orderMemberName=orderMemberName;
        this.orderPrice=orderPrice;
        this.orderStatus=OrderStatus.PENDING;
        this.orderTime=LocalDateTime.now();
    }

    public void setOrder(Member member){
        this.member=member;
        if(!member.getOrders().contains(this)){
            member.getOrders().add(this);
        }
    }
}
