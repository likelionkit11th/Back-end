package com.mutsa.homework5.service;

import com.mutsa.homework5.domain.OrderProduct;

import java.util.Optional;

public interface OrderProductService {
    public OrderProduct save(OrderProduct orderProduct);
    public Optional<OrderProduct> findById(Long id);
}
