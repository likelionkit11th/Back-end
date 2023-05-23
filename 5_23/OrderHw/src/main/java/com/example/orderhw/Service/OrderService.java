package com.example.orderhw.Service;

import com.example.orderhw.DTO.OrderDTO;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional//(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    public Long Create(OrderDTO orderDTO){
        Order order= orderDTO.toEntity();
        orderRepository.save(order);
        return order.getId();
    }
}
