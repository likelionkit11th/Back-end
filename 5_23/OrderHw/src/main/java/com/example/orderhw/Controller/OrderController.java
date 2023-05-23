package com.example.orderhw.Controller;

import com.example.orderhw.DTO.OrderDTO;
import com.example.orderhw.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Long> memberCreate(@RequestBody OrderDTO orderDTO) {
        Long orderId = orderService.Create(orderDTO);
        return ResponseEntity.created(URI.create("/order/" + orderId)).body(orderId);
    }
}
