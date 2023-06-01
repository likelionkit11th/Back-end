package com.example.orderhw.DTO.Create;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Status.OrderProductStatus;

public record OrderProductDto(String productName, Long productPrice, Long productCount, OrderProductStatus productStatus) {
    public OrderProduct toEntity(){
        return OrderProduct.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productCount(productCount)
                .build();
    }
}

