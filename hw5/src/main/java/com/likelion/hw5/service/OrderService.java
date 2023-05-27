package com.likelion.hw5.service;


import com.likelion.hw5.domain.*;
import com.likelion.hw5.repository.CancelHistoryRepository;
import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import com.likelion.hw5.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CancelHistoryRepository cancelHistoryRepository;

    public List<Order> getOrderList(){
        return  StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Order> findOrderListByUsername(Pageable pageable, String username){
        return orderRepository.findOrdersByUsername(username, pageable).toList();
    }
    public Long order(List<OrderItemDto> items, Long userId){
        User findUser = userRepository.findById(userId).
                orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다."));


        Order order = Order.builder()
                .user(findUser)
                .orderedAt(LocalDateTime.now())
                .build();

        for(OrderItemDto orderItemDto : items){
            Item findItem = itemRepository.findById(orderItemDto.getItemId())
                    .orElseThrow(()->new NoSuchElementException("해당 상품을 찾을 수 없습니다."));

            OrderItem orderItem = OrderItem.builder()
                    .item(findItem)
                    .order(order)
                    .cancelHistory(null)
                    .stockQuantity(orderItemDto.getStockQuantity())
                    .status(OrderItem.OrderItemStatus.ORDERED)
                    .build();

            order.addOrderItem(orderItem);

        }

        orderRepository.save(order);

        return order.getId();
    }


    public void cancel(Long orderId, List<Long> orderItemIdList, Long userId){

        // validate parameters
        Order findOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("잘못된 Order 정보입니다."));

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("잘못된 User 정보입니다."));


        // 취소될 Order Item들
        List<OrderItem> cancelOrderItems = findOrder.getOrderItems().stream()
                .filter(oi -> orderItemIdList.contains(oi.getId())).toList();



        //Cancel history를 repository를 사용하지 않고 자동 저장할수는 없을까?
        CancelHistory cancelHistory = CancelHistory.builder()
                .order(findOrder)
                .canceledAt(LocalDateTime.now())
                .canceledOrderItems(new ArrayList<>(cancelOrderItems))
                .build();

        cancelHistoryRepository.save(cancelHistory);

        cancelOrderItems.forEach(orderItem -> {
            orderItem.setCancelHistory(cancelHistory);
            orderItem.setStatus(OrderItem.OrderItemStatus.CANCELED);
        });

        findOrder.addCancelHistory(cancelHistory);
    }


    @Getter
    @Builder
    public static class OrderItemDto{
        private Long itemId;
        private Integer stockQuantity;
    }
}
