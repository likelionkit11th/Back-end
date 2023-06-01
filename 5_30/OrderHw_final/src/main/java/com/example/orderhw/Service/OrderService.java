package com.example.orderhw.Service;

import com.example.orderhw.DTO.Create.CancelDto;
import com.example.orderhw.DTO.Create.OrderDto;
import com.example.orderhw.DTO.Create.OrderProductDto;
import com.example.orderhw.DTO.ToBody.MemberOutDto;
import com.example.orderhw.DTO.ToBody.OrderOutDto;
import com.example.orderhw.DTO.ToBody.OrderProductOutDto;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Repository.CancelRepository;
import com.example.orderhw.Repository.MemberRepository;
import com.example.orderhw.Repository.OrderProductRepository;
import com.example.orderhw.Repository.OrderRepository;

import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional//(readOnly=true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    private final MemberRepository memberRepository;
    private final CancelRepository cancelRepository;
    public List<OrderOutDto> getOrderPage(Pageable pageable) {
        pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC,"id"));
        List<Order> all = orderRepository.findAll();
        List<OrderOutDto> orderOutDtoPage= new ArrayList<>();
        for(Order order: all){
            OrderOutDto orderOutDto = OrderOutDto.builder()
                    .id(order.getId())
                    .memberId(order.getMemberId())
                    .orderMemberName(order.getOrderMemberName())
                    .orderPrice(order.getOrderPrice())
                    .orderStatus(order.getOrderStatus())
                    .orderTime(order.getOrderTime())
                    .orderProducts(getOrderProductList(order))
                    .build();
            orderOutDtoPage.add(orderOutDto);
        }
        Page<OrderOutDto> orderAll= new PageImpl<>(orderOutDtoPage,pageable,orderOutDtoPage.size());
        List<OrderOutDto> orderPage = orderAll.getContent();
        return orderPage;
    }
    public List<OrderProductOutDto> getOrderProductList(Order order){
        List<OrderProduct> all =orderProductRepository.findByorderidvalue(order);
        List<OrderProductOutDto> orderProductOutDtoList= new ArrayList<>();
        for(OrderProduct orderProduct: all){
            OrderProductOutDto orderProductOutDto = OrderProductOutDto.builder()
                    .id(orderProduct.getId())
                    .orderId(orderProduct.getOrderId())
                    .cancelLogId(orderProduct.getCancelId())
                    .productName(orderProduct.getProductName())
                    .productPrice(orderProduct.getProductPrice())
                    .productCount(orderProduct.getProductCount())
                    .productStatus(orderProduct.getProductStatus())
                    .build();
            orderProductOutDtoList.add(orderProductOutDto);
        }
        return orderProductOutDtoList;
    }
    public List<OrderOutDto> getOrderSearchPage(String keyword, Pageable pageable){
        pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC,"id"));
        List<Order> search = orderRepository.findByOrderMemberNameLike(keyword,pageable).getContent();
        List<OrderOutDto> orderOutDtoPage= new ArrayList<>();
        for(Order order: search){
            OrderOutDto orderOutDto = OrderOutDto.builder()
                    .id(order.getId())
                    .memberId(order.getMemberId())
                    .orderMemberName(order.getOrderMemberName())
                    .orderPrice(order.getOrderPrice())
                    .orderStatus(order.getOrderStatus())
                    .orderTime(order.getOrderTime())
                    .orderProducts(getOrderProductList(order))
                    .build();
            orderOutDtoPage.add(orderOutDto);
        }
        Page<OrderOutDto> orderAll= new PageImpl<>(orderOutDtoPage,pageable,orderOutDtoPage.size());
        List<OrderOutDto> orderPage = orderAll.getContent();
        return orderPage;
    }
    public List<OrderProductOutDto> getOrderProductPage(Pageable pageable) {
        pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC,"id"));
        List<OrderProduct> all = orderProductRepository.findAll();
        List<OrderProductOutDto> orderProductOutDtoList= new ArrayList<>();
        for(OrderProduct orderProduct: all){
            OrderProductOutDto orderProductOutDto = OrderProductOutDto.builder()
                    .id(orderProduct.getId())
                    .orderId(orderProduct.getOrderId())
                    .cancelLogId(orderProduct.getCancelId())
                    .productName(orderProduct.getProductName())
                    .productPrice(orderProduct.getProductPrice())
                    .productCount(orderProduct.getProductCount())
                    .productStatus(orderProduct.getProductStatus())
                    .build();
            orderProductOutDtoList.add(orderProductOutDto);
        }

        Page<OrderProductOutDto> orderProductAll= new PageImpl<>(orderProductOutDtoList, pageable, orderProductOutDtoList.size());
        List<OrderProductOutDto> orderProductPage = orderProductAll.getContent();

        return orderProductPage;
    }

    public OrderOutDto findOneOrder(Long orderId) throws Exception{
        Order order =orderRepository
                .findById(orderId)
                .orElseThrow(()-> new RuntimeException());
        OrderOutDto orderOutDto = OrderOutDto.builder()
                .id(order.getId())
                .memberId(order.getMemberId())
                .orderMemberName(order.getOrderMemberName())
                .orderPrice(order.getOrderPrice())
                .orderStatus(order.getOrderStatus())
                .orderTime(order.getOrderTime())
                .orderProducts(getOrderProductList(order))
                .build();
        return orderOutDto;
    }

    public OrderProductOutDto findOneOrderProduct(Long orderId,Long orderProductId) throws Exception{
        Order order =orderRepository
                .findById(orderId)
                .orElseThrow(()-> new RuntimeException());
        List<OrderProduct> orderProductList=orderProductRepository.findByorderidvalue(order);
        OrderProduct orderProduct = orderProductList.get(orderProductId.intValue()-1);
        OrderProductOutDto orderProductOutDto = OrderProductOutDto.builder()
                .id(orderProduct.getId())
                .orderId(orderProduct.getOrderId())
                .cancelLogId(orderProduct.getCancelId())
                .productName(orderProduct.getProductName())
                .productPrice(orderProduct.getProductPrice())
                .productCount(orderProduct.getProductCount())
                .productStatus(orderProduct.getProductStatus())
                .build();
        return orderProductOutDto;
    }

    public Long addOrder(OrderDto orderDto, List<OrderProductDto> orderProductDtoList){
        Member member = memberRepository
                    .findById(orderDto.memberId())
                    .orElseThrow(() -> new RuntimeException());
        Order order = orderDto.toEntity(member);
        for(OrderProductDto orderProductDto : orderProductDtoList) {
            OrderProduct orderProduct = orderProductDto.toEntity();
            order.setOrderProduct(orderProduct);
            orderProductRepository.save(orderProduct);
        }
        orderRepository.save(order);
        return order.getId();
    }

    public void editOrder(Long orderId, OrderDto orderDto,List<OrderProductDto> orderProductDtoList) throws Exception{
        Member member = memberRepository
                .findById(orderDto.memberId())
                .orElseThrow(() -> new RuntimeException());
        Order beforeOrder = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException());
        Order orderChange =orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException())
                .update(orderDto.orderMemberName(),orderDto.orderPrice(), orderDto.orderStatus(),member);

        orderProductRepository.deleteByorderidvalue(beforeOrder);
        for(OrderProductDto orderProductDto : orderProductDtoList) {
            OrderProduct orderProduct = orderProductDto.toEntity();
            orderChange.setOrderProduct(orderProduct);
            orderProductRepository.save(orderProduct);
        }
    }

    public void deleteOrder(Long orderId) throws Exception {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(()-> new RuntimeException());
        if(order.getOrderStatus()!=OrderStatus.CANCELED) {
            CancelDto cancelDto = new CancelDto(order.getId(), order.getOrderPrice());
            CancelLog cancelLog = cancelDto.toEntity(order);
            order.setOrderCancel(cancelLog);
            cancelRepository.save(cancelLog);
        }else{
            throw new RuntimeException();
        }

    }
    public void deleteOrderProduct(Long orderId,Long orderProductId) throws Exception {
        Order order =orderRepository
                .findById(orderId)
                .orElseThrow(()-> new RuntimeException());
        List<OrderProduct> orderProductList=orderProductRepository.findByorderidvalue(order);
        OrderProduct orderProduct = orderProductList.get(orderProductId.intValue()-1);
        if(orderProduct.getProductStatus()!=OrderProductStatus.ERROR) {
            order.setOneOrderCancel(orderProductId);
            orderProduct.setProductStatus(OrderProductStatus.ERROR);
        }else{
            throw new RuntimeException();
        }

    }





}
