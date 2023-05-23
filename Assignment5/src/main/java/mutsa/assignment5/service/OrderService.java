package mutsa.assignment5.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutsa.assignment5.dto.AddOrderDto;
import mutsa.assignment5.dto.AddOrderProductDto;
import mutsa.assignment5.dto.CreateOrderDto;
import mutsa.assignment5.entity.Member;
import mutsa.assignment5.entity.Order;
import mutsa.assignment5.entity.OrderProduct;
import mutsa.assignment5.repository.MemberRepository;
import mutsa.assignment5.repository.OrderProductRepository;
import mutsa.assignment5.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository ordersRepository;
    private final OrderProductRepository orderProductRepository;


    @Transactional
    public Optional<Long> createOrder(CreateOrderDto createOrderDto){
        Member member = memberRepository.findById(createOrderDto.getOrder().getMemberId()).get();
        AddOrderDto orderDto = createOrderDto.getOrder();

        List<OrderProduct> orderProducts = new ArrayList<>();

        for(AddOrderProductDto product : createOrderDto.getOrderProducts()){
            orderProducts.add(product.toEntity());
        }

        Order order = Order.newOrder(member, orderDto.getOrderName(), orderDto.getOrderPrice(), orderProducts);
        ordersRepository.save(order);
        return Optional.ofNullable(order.getOrderId());
    }



}
