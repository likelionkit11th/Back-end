package com.example.orderhw.DTO.Create;

import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Order;


public record CancelDto(Long orderId, Long cancelPrice) {
    public CancelLog toEntity(Order order){
        return CancelLog.builder()
                .order(order)
                .cancelPrice(order.getOrderPrice())
                .build();
    }
}