package com.mutsa.homework5.service;

import com.mutsa.homework5.domain.Order;

import java.util.Optional;

public interface OrderService {

    public Order save(Order order);
    public Optional<Order> findById(Long id);

}
