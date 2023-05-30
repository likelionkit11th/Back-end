package com.example.orderhw.DTO;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Status.OrderProductStatus;

public record OrderProductDto(Order order, String productName, Long productPrice, Long productCount, OrderProductStatus productStatus) {
    public OrderProduct toEntity(){
        return OrderProduct.builder()
                .order(order)
                .productName(productName)
                .productPrice(productPrice)
                .productCount(productCount)
                .productStatus(productStatus)
                .build();
    }
}

