package com.likelion.hw5.service;

import com.likelion.hw5.domain.Item;
import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    private List<Item> testItems;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public OrderService orderService(OrderRepository orderRepository, ItemRepository itemRepository) {
            return new OrderService(orderRepository, itemRepository);
        }
    }


    @BeforeEach
    void setUp() {
        testItems = List.of(
                new Item("물건1", 5000),
                new Item("물건2", 3000),
                new Item("물건3", 4000));

        orderRepository.deleteAll();
        itemRepository.deleteAll();
        itemRepository.saveAll(testItems);
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


    @Test
    @DisplayName("정상 주문시 예외를 throw하지 않음")
    void t2() throws Exception {
        //given

        //when
        assertThatCode(()->orderService.order(List.of(new OrderService.OrderItemDto(1L, 5))))
                .doesNotThrowAnyException();

        //then
    }

    @Test
    @DisplayName("잘못된 주문 : 없는 Item Id를 인자로 넘겨주었을떄 예외 발생")
    void t3() throws Exception {
        //given

        //when
        assertThatThrownBy(()->orderService.order(List.of(new OrderService.OrderItemDto(10L, 5))))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품을 찾을 수 없습니다.");
        //then
    }
}