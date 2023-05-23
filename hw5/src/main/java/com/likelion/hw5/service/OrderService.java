package com.likelion.hw5.service;


import com.likelion.hw5.domain.Order;
import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    public void order(List<OrderItemDto> items){
        Order order = new Order();

        for(OrderItemDto item : items){


        }

    }



    @Getter
    @Builder
    public static class OrderItemDto{
        private Long itemId;
        private Integer stockQuantity;
    }
}
