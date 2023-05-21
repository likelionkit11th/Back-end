package com.seoljy.hw5tablemapping.dto;

import com.seoljy.hw5tablemapping.domain.Member;
import com.seoljy.hw5tablemapping.domain.Order;
import com.seoljy.hw5tablemapping.domain.OrderStatus;

public record OrderDTO(String orderName, Long orderPrice) {
    public Order toEntity() {
        return Order.builder().orderName(orderName).orderPrice(orderPrice).build();
    }
}








