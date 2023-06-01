package com.example.orderhw.Controller;

import com.example.orderhw.DTO.Create.OrderCreateDto;
import com.example.orderhw.DTO.Create.OrderDto;
import com.example.orderhw.DTO.Create.OrderProductDto;
import com.example.orderhw.DTO.ToBody.OrderOutDto;
import com.example.orderhw.DTO.ToBody.OrderProductOutDto;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Long> addOrder(@RequestBody OrderCreateDto orderCreateDto) {
        OrderDto orderDto = orderCreateDto.getOrderDto();
        List<OrderProductDto> orderProducts = orderCreateDto.getOrderProductDto();
        Long orderId = orderService.addOrder(orderDto,orderProducts);
        return ResponseEntity.created(URI.create("orders/" + orderId)).body(orderId);
    }

    @PutMapping("orders/{orderId}")
    public ResponseEntity<Void> editOrder(@PathVariable Long orderId, @RequestBody OrderCreateDto orderCreateDto) throws Exception {
        orderService.editOrder(orderId,orderCreateDto.getOrderDto(),orderCreateDto.getOrderProductDto());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws Exception {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("orders/{orderId}/{orderProductId}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long orderId, @PathVariable Long orderProductId) throws Exception {
        orderService.deleteOrderProduct(orderId,orderProductId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("orders/{orderId}")  //
    public ResponseEntity<OrderOutDto> findOneOrder(@PathVariable Long orderId) throws Exception {
        OrderOutDto orderOutDto = orderService.findOneOrder(orderId);
        return ResponseEntity.ok().body(orderOutDto);
    }
    @GetMapping("orders/{orderId}/{orderProductId}")  //
    public ResponseEntity<OrderProductOutDto> findOneOrderProduct(@PathVariable Long orderId,@PathVariable Long orderProductId) throws Exception {
        OrderProductOutDto orderProductOutDto = orderService.findOneOrderProduct(orderId,orderProductId);
        return ResponseEntity.ok().body(orderProductOutDto);
    }


    @GetMapping("orderlist")
    public ResponseEntity<List<OrderOutDto>> findAllOrders(
            @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        List<OrderOutDto> orders = orderService.getOrderPage(pageable);
        return ResponseEntity.ok().body(orders);
    }
    @GetMapping("orderproductlist")
    public ResponseEntity<List<OrderProductOutDto>> findAllOrderProducts(
            @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        List<OrderProductOutDto> orderproducts = orderService.getOrderProductPage(pageable);
        return ResponseEntity.ok().body(orderproducts);

    }

    @GetMapping("orderlist/search")
    public ResponseEntity<List<OrderOutDto>> findSearchOrders(
            @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pagesize,
            @RequestParam(value = "keyword",required = true) String keyword){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        List<OrderOutDto> orderSearch = orderService.getOrderSearchPage(keyword, pageable);
        return ResponseEntity.ok().body(orderSearch);

    }
}
