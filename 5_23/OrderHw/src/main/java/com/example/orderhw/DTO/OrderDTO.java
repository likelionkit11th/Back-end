package com.example.orderhw.DTO;

import com.example.orderhw.Domain.Order;

public record OrderDTO(String orderMemberName, Long orderPrice){
    public Order toEntity(){
        return Order.builder()
                .orderMemberName(orderMemberName)
                .orderPrice(orderPrice)
                .build();
    }
}
