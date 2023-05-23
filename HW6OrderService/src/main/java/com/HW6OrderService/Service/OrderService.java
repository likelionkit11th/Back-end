package com.HW6OrderService.Service;

import com.HW6OrderService.DTO.OrderDTO;
import com.HW6OrderService.Domain.Member;
import com.HW6OrderService.Domain.Order;
import com.HW6OrderService.Repository.MemberRepository;
import com.HW6OrderService.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final  OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public void createOrder(OrderDTO orderDTO) {
        Member member = memberRepository
                .findById(orderDTO.memberId())
                .orElseThrow(()-> new NoSuchElementException("사용자가 존재하지 않습니다."));
        Order order = orderDTO.toEntity(member);
        orderRepository.save(order);
    }
}