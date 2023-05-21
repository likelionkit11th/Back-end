package com.seoljy.hw5tablemapping.service;

import com.seoljy.hw5tablemapping.domain.Order;
import com.seoljy.hw5tablemapping.domain.OrderProduct;
import com.seoljy.hw5tablemapping.domain.OrderStatus;
import com.seoljy.hw5tablemapping.dto.OrderDTO;
import com.seoljy.hw5tablemapping.dto.OrderProductDTO;
import com.seoljy.hw5tablemapping.repository.DataJpaOrderProductRepository;
import com.seoljy.hw5tablemapping.repository.DataJpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderService {
    private final DataJpaOrderRepository dataJpaOrderRepository;

    public void createOrder(OrderDTO orderDTO) {
        Order order = orderDTO.toEntity();
        order.setOrderStatus(OrderStatus.PENDING);


        dataJpaOrderRepository.save(order);
    }
}
