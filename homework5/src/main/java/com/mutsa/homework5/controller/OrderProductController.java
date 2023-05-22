package com.mutsa.homework5.controller;

import com.mutsa.homework5.domain.OrderProduct;
import com.mutsa.homework5.service.OrderProductService;
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
public class OrderProductController {

    @Autowired
    private final OrderProductService orderProductService;

    @GetMapping("/orderProducts/{id}")
    public ResponseEntity<Optional<OrderProduct>> getOrderProductById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(orderProductService.findById(id));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @PostMapping("/orderProducts")
    public ResponseEntity<OrderProduct> createOrderProduct(@RequestBody OrderProduct orderProduct) {
        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(orderProductService.save(orderProduct));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
