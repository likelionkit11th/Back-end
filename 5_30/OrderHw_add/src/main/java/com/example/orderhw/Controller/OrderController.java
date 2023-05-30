package com.example.orderhw.Controller;

import com.example.orderhw.DTO.OrderDto;
import com.example.orderhw.DTO.OrderProductDto_2;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order/")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("orders")
    public ResponseEntity<Long> addOrder(@RequestBody OrderDto orderDto, @RequestBody OrderProductDto_2 orderProductDto_2) {
        Long userId = orderService.addOrder(orderDto, orderProductDto_2);
        return ResponseEntity.created(URI.create("orders/" + userId)).body(userId);

    }

    @PutMapping("orders/{orderId}")
    public ResponseEntity<Void> editOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto, @RequestBody OrderProductDto_2 orderProductDto_2) throws Exception {
        orderService.editOrder(orderDto, orderId, orderProductDto_2);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws Exception {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("orders/{orderId}")
    public ResponseEntity<Order> findOneOrder(@PathVariable Long orderId) throws Exception {
        Order order = orderService.findOneOrder(orderId);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("orderlist")
    public ResponseEntity<List<Order>> findAllOrders(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<Order> orders = orderService.getOrderPage(pageable);
        return ResponseEntity.ok().body(orders);
    }
    @GetMapping("orderproductlist")
    public ResponseEntity<List<OrderProduct>> findAllOrderProducts(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<OrderProduct> orderproducts = orderService.getOrderProductPage(pageable);
        return ResponseEntity.ok().body(orderproducts);

    }

    @GetMapping("orderlist/{keyword}")
    public ResponseEntity<List<Order>> findSearchOrders(@PathVariable String keyword, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<Order> orderSearch = orderService.getOrderSearchPage(keyword, pageable);
        return ResponseEntity.ok().body(orderSearch);

    }
}
