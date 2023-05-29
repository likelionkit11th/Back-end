package com.seoljy.hw6orderservice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name="member_name")
    private String name;


    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



    @Builder
    public Member(String name) {
        this.name = name;
    }



    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setMember(null);
    }

    public void updateOrder(Long orderId, Order updatedOrder) {
        for (Order existingOrder : orders) {
            if (existingOrder.getId().equals(orderId)) {
                existingOrder.setOrderName(updatedOrder.getOrderName());
                existingOrder.setOrderPrice(updatedOrder.getOrderPrice());
                break;
            }
        }
    }
}
