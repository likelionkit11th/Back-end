package com.likelion.hw5.controller;


import com.likelion.hw5.domain.Order;
import com.likelion.hw5.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/list")
    public List<Order> getOrders(){
        return orderService.getOrderList();
    }

    @GetMapping("/list/search")
    public List<Order> search(
            @RequestParam(required = false, defaultValue = "*")String username,
            @PageableDefault(sort = {"orderedAt"},direction = Sort.Direction.DESC) Pageable pageable
    ){
        return orderService.findOrderListByUsername(pageable, username);
    }

    @PostMapping("/")
    public String order(@RequestBody OrderRequest orderRequest, HttpServletRequest req){
        Long userId = (Long) req.getSession().getAttribute("userId");
        orderService.order(orderRequest.getOrderItemDtos(), userId);

        return "redirect:/";
    }

    @PostMapping("/{orderId}/cancel")
    public String cancel(@PathVariable Long orderId, @RequestBody CancelRequestBody cancelRequestBody, HttpServletRequest req){
        Long userId = (Long) req.getSession().getAttribute("userId");
        orderService.cancel(orderId,cancelRequestBody.getOrderItemIdList(), userId);

        return "redirect:/";
    }
    @Data
    public static class OrderRequest{
        List<OrderService.OrderItemDto> orderItemDtos;
    }

    @Data
    public static class CancelRequestBody{
        List<Long> orderItemIdList;
    }
}
