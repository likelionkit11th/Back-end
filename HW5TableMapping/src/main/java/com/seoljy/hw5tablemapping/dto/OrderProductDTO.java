package com.seoljy.hw5tablemapping.dto;

import com.seoljy.hw5tablemapping.domain.OrderProduct;

public record OrderProductDTO(String orderProductName, Long orderProductPrice, Long orderProductQuantity) {
    public OrderProduct toEntity() {
        return OrderProduct.builder()
                .orderProductName(orderProductName)
                .orderProductPrice(orderProductPrice)
                .orderProductQuantity(orderProductQuantity)
                .build();
    }
}