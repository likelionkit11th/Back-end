package likelion.assignment6.service;

import likelion.assignment6.dto.AddOrderDto;
import likelion.assignment6.dto.AddOrderProductDto;
import likelion.assignment6.dto.CreateOrderDto;
import likelion.assignment6.dto.PaymentDetailDto;
import likelion.assignment6.entity.Member;
import likelion.assignment6.entity.Order;
import likelion.assignment6.entity.OrderProduct;
import likelion.assignment6.entity.PaymentDetail;
import likelion.assignment6.repository.MemberRepository;
import likelion.assignment6.repository.OrderProductRepository;
import likelion.assignment6.repository.OrderRepository;
import likelion.assignment6.repository.PaymentDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final PaymentDetailRepository paymentDetailRepository;


    @Transactional
    public Optional<Long> createOrder(CreateOrderDto createOrderDto){
        Member member = memberRepository.findById(createOrderDto.getOrder().getMemberId()).get();
        AddOrderDto orderDto = createOrderDto.getOrder();
        PaymentDetailDto paymentDetailDto = createOrderDto.getPaymentDetail();

        List<OrderProduct> orderProducts = new ArrayList<>();

        for(AddOrderProductDto product : createOrderDto.getOrderProducts()){
            orderProducts.add(product.toEntity());
        }

        Order order = Order.newOrder(member, orderDto.getOrderName(), orderDto.getOrderPrice(), orderProducts);
        ordersRepository.save(order);

        PaymentDetail paymentDetail = PaymentDetail.newPaymentDetail(order, paymentDetailDto.getActualPaymentAmount());
        paymentDetailRepository.save(paymentDetail);

        return Optional.ofNullable(order.getOrderId());
    }

    @Transactional
    public Optional<List<Order>> findOrder(String name){
        return ordersRepository.findByOrderName(name);
    }

    @Transactional
    public Optional<List<Order>> findOrderByPrice(Long small, Long big){
        return ordersRepository.findByPrices(small, big);

    }

    @Transactional
    public Optional<Order> findByOrderNameAndPrice(String name, Long price){
        return ordersRepository.findByOrderNameAndOrderPrice(name, price);

    }

    @Transactional
    public Page<PaymentDetail> findPaymentDetail(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return paymentDetailRepository.findAllByOrderByPaymentDateDesc(pageRequest);


    }



}
