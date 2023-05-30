package com.example.orderhw.Service;

import com.example.orderhw.DTO.OrderDto;
import com.example.orderhw.DTO.OrderProductDto;
import com.example.orderhw.DTO.OrderProductDto_2;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Repository.OrderProductRepository;
import com.example.orderhw.Repository.OrderRepository;
import com.example.orderhw.Status.CancelLogStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional//(readOnly=true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    public List<Order> getOrderPage(Pageable pageable) {
        Page<Order> all = orderRepository.findAll(pageable);
        List<Order> orderpage = all.getContent();
        return orderpage;
    }

    public List<Order> getOrderSearchPage(String keyword, Pageable pageable){
        Page<Order> search = orderRepository.findByOrderMemberNameLike(keyword,pageable);
        List<Order> ordersearchpage= search.getContent();
        return ordersearchpage;
    }
    public List<OrderProduct> getOrderProductPage(Pageable pageable) {
        Page<OrderProduct> all = orderProductRepository.findAll(pageable);
        List<OrderProduct> orderproductpage = all.getContent();

        return orderproductpage;
    }

    public Long addOrder(OrderDto orderDto, OrderProductDto_2 orderProductDto_2){
        Order order = orderDto.toEntity();
        OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .productName(orderProductDto_2.productName())
                .productPrice(orderProductDto_2.productPrice())
                .productCount(orderProductDto_2.productCount())
                .productStatus(orderProductDto_2.productStatus())
                .build();
        orderRepository.save(order);
        orderProductRepository.save(orderProduct);
        return order.getId();
    }

    public void editOrder(OrderDto orderDto, Long orderId, OrderProductDto_2 orderProductDto_2) throws Exception{
        Order order = orderDto.toEntity();
        orderProductRepository
                .findByOrder(order)
                .orElseThrow(() -> new RuntimeException())
                .update(order, orderProductDto_2.productName(),orderProductDto_2.productPrice(), orderProductDto_2.productCount(),orderProductDto_2.productStatus());
        orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException())
                .update(orderDto.orderMemberName(), orderDto.orderPrice(),orderDto.orderStatus(),orderDto.member());
    }

    public Order findOneOrder(Long orderId) throws Exception{
        return orderRepository
                .findById(orderId)
                .orElseThrow(()-> new RuntimeException());
    }

    public void deleteOrder(Long orderId) throws Exception {
        Order order = findOneOrder(orderId);
        CancelLog cancelLog = CancelLog.builder()
                .order(order)
                .cancelPrice(order.getOrderPrice())
                .cancelLogStatus(CancelLogStatus.REQUESTED)
                .build();
        orderProductRepository.deleteByOrder(order);
        orderRepository.deleteById(orderId);

    }
}
