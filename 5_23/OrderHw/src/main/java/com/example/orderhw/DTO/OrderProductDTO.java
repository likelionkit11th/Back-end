package com.example.orderhw.DTO;

import com.example.orderhw.Domain.OrderProduct;

public record OrderProductDTO(String productName, Long productPrice, Long productCount) {
    public OrderProduct toEntity(){
        return OrderProduct.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productCount(productCount)
                .build();
    }
}
