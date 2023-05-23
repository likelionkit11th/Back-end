package com.likelion.hw5.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@NoArgsConstructor

public class Order {
    @Builder
    public Order(UserEntity user, LocalDateTime orderedAt) {
        this.user = user;
        this.orderedAt = orderedAt;
    }

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity user;

    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }

    // 주문 금액을 Database에 저장하는게 아니라, OrderItem에서 계산하도록 설정 ... 데이터가 중복되기 때문
    public Integer getOrderPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::getOrderItemTotalPrice).sum();
    }
}
