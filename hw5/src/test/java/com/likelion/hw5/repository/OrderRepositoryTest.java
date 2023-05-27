package com.likelion.hw5.repository;

import com.likelion.hw5.domain.Order;
import com.likelion.hw5.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
public class OrderRepositoryTest {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    @BeforeEach
    void setUp() {
        // 테스트 데이터 생성
        User user1 = new User("John Doe");
        User user2 = new User("Jane Smith");
        Order order1 = new Order(user1, LocalDateTime.now());
        Order order2 = new Order(user2, LocalDateTime.now());
        Order order3 = new Order(user1, LocalDateTime.now());

        userRepository.save(user1);
        userRepository.save(user2);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }

    @Test
    @DisplayName("orderRepository 객체 생성")
    void t1() throws Exception {
        //then
        assertThat(orderRepository).isNotNull();
    }

    @Test
    @DisplayName("username으로 조회")
    public void testFindOrdersByUsername() {


        // 주문 검색 테스트
        String username = "John";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orders = orderRepository.findOrdersByUsername(username, pageable);

        // 검증
        assertThat(2).isEqualTo(orders.getTotalElements());
    }
}


