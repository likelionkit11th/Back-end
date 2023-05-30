package likelion.assignment6.controller;

import likelion.assignment6.dto.CreateOrderDto;
import likelion.assignment6.dto.OrderDto;
import likelion.assignment6.dto.PaymentDetailDto;
import likelion.assignment6.entity.Order;
import likelion.assignment6.entity.PaymentDetail;
import likelion.assignment6.service.MemberService;
import likelion.assignment6.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    @PostMapping("/post/orders")
    public ResponseEntity<Long> addOrder(@RequestBody CreateOrderDto createOrderDto) throws RuntimeException {
        Long orderId = orderService.createOrder(createOrderDto).get();
        return ResponseEntity.ok().body(orderId);
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> findOrder(@RequestParam(value="name", required = false) String name) throws RuntimeException {
        List<Order> orders = orderService.findOrder(name)
                .orElseThrow(() -> new RuntimeException("해당하는 주문이 없습니다"));
        List<OrderDto> orderDtoList = new ArrayList<>();

        for(Order order : orders){
            orderDtoList.add(Order.toDto(order));
        }

        return ResponseEntity.ok().body(orderDtoList);
    }

    @GetMapping("/order/price")
    public ResponseEntity<List<OrderDto>> findOrderByPrice(@RequestParam(value="small", required = true) Long small, @RequestParam(value="big", required = true) Long big) throws RuntimeException {
        List<Order> orders = orderService.findOrderByPrice(small, big)
                .orElseThrow(() -> new RuntimeException("해당하는 주문이 없습니다"));
        List<OrderDto> orderDtoList = new ArrayList<>();

        for(Order order : orders){
            orderDtoList.add(Order.toDto(order));
        }

        return ResponseEntity.ok().body(orderDtoList);
    }

    @GetMapping("/test/order")
    public ResponseEntity<OrderDto> getOrder(@RequestParam(value="name", required = false) String name, @RequestParam(value="price", required = true) Long price) throws RuntimeException {
        Order order = orderService.findByOrderNameAndPrice(name, price)
                .orElseThrow(() -> new RuntimeException("해당하는 주문이 없습니다"));

        OrderDto orderDto = Order.toDto(order);
        return ResponseEntity.ok().body(orderDto);
    }

    @GetMapping("/page")
    public ResponseEntity<List<PaymentDetailDto>> getPaymentDetail(@RequestParam int page, @RequestParam int size){
        Page<PaymentDetail> paymentDetailPage = orderService.findPaymentDetail(page-1, size);

        List<PaymentDetail> paymentDetails = paymentDetailPage.getContent();
        List<PaymentDetailDto> paymentDetailDtoList = new ArrayList<>();

        for(PaymentDetail paymentDetail : paymentDetails){
            paymentDetailDtoList.add(PaymentDetail.toDto(paymentDetail));
        }

        return ResponseEntity.ok().body(paymentDetailDtoList);
    }



}
