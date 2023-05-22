package com.mutsa.homework5.service;


import com.mutsa.homework5.domain.Order;
import com.mutsa.homework5.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order){
        try {
            return orderRepository.save(
                    new Order(
                            order.getOrderMemberName(),
                            order.getOrderPrice()
                    )
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if(order.isPresent()){
                return order;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}