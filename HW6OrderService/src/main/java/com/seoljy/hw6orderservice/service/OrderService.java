package com.seoljy.hw6orderservice.service;

import com.seoljy.hw6orderservice.domain.Order;
import com.seoljy.hw6orderservice.repository.DataJpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private DataJpaOrderRepository dataJpaOrderRepository;

    public List<Order> findOrdersByUsername(Pageable pageable, String username){
        return dataJpaOrderRepository.findOrdersByUsername(username, pageable).toList();
    }
}
