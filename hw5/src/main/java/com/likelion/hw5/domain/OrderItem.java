package com.likelion.hw5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="cancel_history_id")
    private CancelHistory cancelHistory; // 취소 내역

    private Integer stockQuantity; // 수량

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    public static enum OrderItemStatus {
        ORDERED, CANCELED, COMPLETED
    }


    public Integer getOrderItemTotalPrice(){
        return item.getUnitPrice() * stockQuantity;
    }
}
