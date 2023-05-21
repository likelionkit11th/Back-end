package com.seoljy.hw5tablemapping.controller;

import com.seoljy.hw5tablemapping.dto.OrderDTO;
import com.seoljy.hw5tablemapping.dto.OrderProductDTO;
import com.seoljy.hw5tablemapping.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Void> creatOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        log.info("[CREATE]");
        return ResponseEntity.created(URI.create("/order")).build();
    }
}