package com.likelion.hw5.service;


import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;


}
