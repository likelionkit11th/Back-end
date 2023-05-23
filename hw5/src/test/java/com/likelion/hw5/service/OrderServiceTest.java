package com.likelion.hw5.service;

import com.likelion.hw5.domain.*;
import com.likelion.hw5.repository.CancelHistoryRepository;
import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import com.likelion.hw5.repository.UserRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    // test용 data
    private List<Item> testItems;
    private UserEntity user;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public OrderService orderService
                (OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository, CancelHistoryRepository cancelHistoryRepository) {
            return new OrderService(orderRepository, itemRepository, userRepository, cancelHistoryRepository);
        }
    }


    @BeforeEach
    void setUp() {
        testItems = List.of(
                new Item("물건1", 5000),
                new Item("물건2", 3000),
                new Item("물건3", 4000));

        user = new UserEntity("test_user");
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();

        itemRepository.saveAll(testItems);
        userRepository.save(user);
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
        assertThatCode(()->orderService.order(List.of(new OrderService.OrderItemDto(testItems.get(0).getId(), 5)), user.getId()))
                .doesNotThrowAnyException();

        //then
    }

    @Test
    @DisplayName("잘못된 주문 : 없는 Item Id를 인자로 넘겨주었을떄 예외 발생")
    void t3() throws Exception {
        //given

        //when
        assertThatThrownBy(()->orderService.order(List.of(new OrderService.OrderItemDto(10L, 5)), user.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품을 찾을 수 없습니다.");
        //then
    }

    @Test
    @DisplayName("잘못된 주문 : 없는 유저")
    void t4() throws Exception {
        //given

        //when
        assertThatThrownBy(()->orderService.order(List.of(new OrderService.OrderItemDto(1L, 5)), 10L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 유저를 찾을 수 없습니다.");
        //then
    }

    @Test
    @DisplayName("Order 객체 생성 및 테이블에 저장")
    void t5() throws Exception {
        //given

        // 인자로 넘겨줄 orderItemDto 생성
        List<OrderService.OrderItemDto> testOrderItemDtos = testItems.stream().map(item -> {
            return OrderService.OrderItemDto
                    .builder()
                    .itemId(item.getId())
                    .stockQuantity(5).build();
        }).toList();

        //when
        // 저장
        Long savedOrderId = orderService.order(testOrderItemDtos, user.getId());

        // 저장한 Order을 조회
        Optional<Order> findOrder = orderRepository.findById(savedOrderId);

        //then

        assertThat(savedOrderId).isNotNull();
        assertThat(findOrder.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Order시 Order 객체의 필드가 잘 매핑되었는지 확인")
    void t6() throws Exception {
        //given
        Order findOrder = saveAndGetOrder();

        //then
        assertThat(findOrder.getOrderItems().size()).isEqualTo(3);
        assertThat(findOrder.getUser()).isEqualTo(user);
        assertThat(findOrder.getOrderedAt()).isNotNull();
    }

    @Test
    @DisplayName("Cancel시 잘못된 파라미터 : 찾을 수 없는 Order")
    void t7() throws Exception {
        //given
        Order findOrder = saveAndGetOrder();
        //when
        List<Long> orderItemIdList = findOrder.getOrderItems().stream().map(OrderItem::getId).toList();

        //then
        assertThatThrownBy(()->orderService.cancel(1000L, orderItemIdList, user.getId()))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Cancel시 잘못된 파라미터 : 찾을수 없는 User")
    void t8() throws Exception {
        //given
        Order findOrder = saveAndGetOrder();
        List<Long> orderItemIdList = findOrder.getOrderItems().stream().map(OrderItem::getId).toList();

        //when
        assertThatThrownBy(()->orderService.cancel(findOrder.getId(), orderItemIdList, 1000L))
                .isInstanceOf(NoSuchElementException.class);
        //then
    }


    @Test
    @DisplayName("Cancel 정상 진행")
    void t9() throws Exception {
        //given
        Order findOrder = saveAndGetOrder();
        List<Long> orderItemIdList = findOrder.getOrderItems().stream().map(OrderItem::getId).toList();

        //when
        orderService.cancel(findOrder.getId(), orderItemIdList, user.getId());

        //then
        final CancelHistory cancelHistory = findOrder.getCancelHistories().get(0);
        assertThat(cancelHistory.getId()).isNotNull();
        assertThat(cancelHistory.getCanceledOrderItems().size()).isEqualTo(3);
    }








    private Order saveAndGetOrder() {
        List<OrderService.OrderItemDto> testOrderItemDtos = testItems.stream().map(item -> {
            return OrderService.OrderItemDto
                    .builder()
                    .itemId(item.getId())
                    .stockQuantity(5).build();
        }).toList();

        Long savedOrderId = orderService.order(testOrderItemDtos, user.getId());
        Order findOrder = orderRepository.findById(savedOrderId).get();
        return findOrder;
    }

}