package com.seoljy.hw6orderservice.controller;

import com.seoljy.hw6orderservice.domain.Order;
import com.seoljy.hw6orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderRestController {
    private final OrderService orderService;

    @GetMapping("/search")
    public ResponseEntity searchOrder(@RequestParam(required = true) String q,
                                      @PageableDefault(sort = {"orderedAt"},direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<Order> orders = orderService.findOrdersByUsername(pageable, q);
        return ResponseEntity.ok().body(orders);
    }
}