package com.likelion.hw5.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelHistory {

    @Id @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;


    private LocalDateTime canceledAt;

    @OneToMany(mappedBy = "cancelHistory")
    private List<OrderItem> canceledOrderItems;

    public Integer getCancelPrice(){
        return canceledOrderItems.stream()
                .mapToInt(OrderItem::getOrderItemTotalPrice).sum();
    }

}
