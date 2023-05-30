package com.example.orderhw.DTO;

import com.example.orderhw.Domain.Member;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Status.OrderStatus;

public record OrderDto(String orderMemberName, Long orderPrice, OrderStatus orderStatus, Member member) {
    public Order toEntity(){
        return Order.builder()
                .orderMemberName(orderMemberName)
                .orderPrice(orderPrice)
                .orderStatus(orderStatus)
                .member(member)
                .build();
    }
}
