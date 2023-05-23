package com.likelion.hw5.service;

import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public OrderService orderService(OrderRepository orderRepository, ItemRepository itemRepository) {
            return new OrderService(orderRepository, itemRepository);
        }
    }

    @Test
    @DisplayName("객체 생성")
    void t1() throws Exception {

        OrderRepository injectedOrderRepository = (OrderRepository) ReflectionTestUtils.getField(orderService, "orderRepository");
        ItemRepository injectedItemRepository = (ItemRepository) ReflectionTestUtils.getField(orderService, "itemRepository");

        //then
        assertThat(this.orderService).isNotNull();
        assertThat(injectedOrderRepository).isNotNull();
        assertThat(injectedItemRepository).isNotNull();


    }

}