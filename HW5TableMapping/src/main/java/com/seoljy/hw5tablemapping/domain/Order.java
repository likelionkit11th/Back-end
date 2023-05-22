package com.seoljy.hw5tablemapping.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orders_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="orders_name")
    String orderName;

    @Column(name="orders_price")
    Long orderPrice;

    @Column(name="orders_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Order(String orderName, Long orderPrice) {
        this.orderName = orderName;
        this.orderPrice = orderPrice;
    }



    public void setMember(Member member) {
        this.member = member;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

