package com.HW6OrderService.DTO;

import com.HW6OrderService.Domain.Member;
import com.HW6OrderService.Domain.Order;
import com.HW6OrderService.Domain.Status.OrderStatus;

public record OrderDTO(Long memberId, String orderPrice, OrderStatus orderStatus) {
    public Order toEntity(Member member) {
        return Order.builder()
                .orderPrice(orderPrice)
                .orderStatus(orderStatus)
                .member(member)
                .build();
    }
}
