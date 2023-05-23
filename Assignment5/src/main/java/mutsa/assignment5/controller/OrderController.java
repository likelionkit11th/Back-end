package mutsa.assignment5.controller;

import lombok.RequiredArgsConstructor;
import mutsa.assignment5.dto.CreateOrderDto;
import mutsa.assignment5.service.MemberService;
import mutsa.assignment5.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    @PostMapping("/post/orders")
    public ResponseEntity<Long> addOrder(@RequestBody CreateOrderDto createOrderDto){
        Long orderId = orderService.createOrder(createOrderDto).get();
        return ResponseEntity.ok().body(orderId);
    }



}
