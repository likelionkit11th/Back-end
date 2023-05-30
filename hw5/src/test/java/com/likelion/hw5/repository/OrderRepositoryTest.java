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

    private Order order1;
    private Order order2;
    private Order order3;

    private User user1;
    private User user2;

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    @BeforeEach
    void setUp() {
        // 테스트 데이터 생성
        user1 = new User("John Doe");
        user2 = new User("Jane Smith");
        order1 = new Order(user1, LocalDateTime.now());
        order2 = new Order(user2, LocalDateTime.now());
        order3 = new Order(user1, LocalDateTime.now());

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

        // when
        String username = "John";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orders = orderRepository.findOrdersByUsername(username, pageable);

        // then
        assertThat(2).isEqualTo(orders.getTotalElements());
        assertThat(orders.getContent()).containsExactly(order1, order3);
    }
}


