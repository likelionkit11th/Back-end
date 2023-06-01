package com.example.orderhw.DTO.Create;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateDto {
    private OrderDto orderDto;
    private List<OrderProductDto> orderProductDto;

    public List<OrderProductDto> getOrderProductDto() {
        return orderProductDto;
    }
}
