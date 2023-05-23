package com.likelion.hw5.service;


import com.likelion.hw5.domain.Item;
import com.likelion.hw5.domain.Order;
import com.likelion.hw5.domain.OrderItem;
import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    public void order(List<OrderItemDto> items){
        Order order = new Order();

        for(OrderItemDto orderItemDto : items){
            Item findItem = itemRepository.findById(orderItemDto.getItemId()).orElseThrow(NoSuchElementException::new);

            OrderItem orderItem = OrderItem.builder()
                    .item(findItem)
                    .order(order)
                    .cancelHistory(null)
                    .stockQuantity(orderItemDto.getStockQuantity())
                    .status(OrderItem.OrderItemStatus.ORDERED)
                    .build();



        }

    }



    @Getter
    @Builder
    public static class OrderItemDto{
        private Long itemId;
        private Integer stockQuantity;
    }
}
