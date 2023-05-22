package com.mutsa.homework5.controller;

import com.mutsa.homework5.domain.Order;
import com.mutsa.homework5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(orderService.findById(id));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(orderService.save(order));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
