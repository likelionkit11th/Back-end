package com.mutsa.homework5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "order_member_name")
    private String orderMemberName;

    @Column(name = "order_price")
    private double orderPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "VARCHAR(20) DEFAULT '접수대기'")
    private OrderStatus status;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<CancellationHistory> cancelHistories = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private PaymentHistory paymentHistory;

    public Order(String orderMemberName, double orderPrice)
    {
        this.orderMemberName = orderMemberName;
        this.orderPrice = orderPrice;
        this.status = OrderStatus.접수대기;
        this.orderTime = LocalDateTime.now();
    }
}
