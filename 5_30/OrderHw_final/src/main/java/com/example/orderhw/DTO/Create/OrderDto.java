package com.example.orderhw.DTO.Create;

import com.example.orderhw.Domain.Member;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.OrderStatus;

public record OrderDto(Long memberId, String orderMemberName, Long orderPrice, OrderStatus orderStatus) {
    public Order toEntity(Member member){
        return Order.builder()
                .orderMemberName(orderMemberName)
                .orderPrice(orderPrice)
                .member(member)
                .build();
    }
}
