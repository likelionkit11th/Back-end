package com.likelion.hw5.service;

import com.likelion.hw5.repository.ItemRepository;
import com.likelion.hw5.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    @DisplayName("객체 생성")
    void t1() throws Exception {

        OrderRepository injectedOrderRepository = (OrderRepository) ReflectionTestUtils.getField(orderService, "orderRepository");
        ItemRepository injectedItemRepository = (ItemRepository) ReflectionTestUtils.getField(orderService, "itemRepository");

        //then
        assertThat(this.orderService).isNotNull();
        assertThat(this.orderRepository).isNotNull();
        assertThat(this.itemRepository).isNotNull();
        assertThat(injectedOrderRepository).isEqualTo(this.orderRepository);
        assertThat(injectedItemRepository).isEqualTo(injectedItemRepository);


    }

}