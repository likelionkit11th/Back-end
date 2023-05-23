package com.HW6OrderService.Controller;

import com.HW6OrderService.DTO.OrderDTO;
import com.HW6OrderService.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
    }
}
