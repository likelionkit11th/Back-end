package com.mutsa.homework5.service;

import com.mutsa.homework5.domain.OrderProduct;
import com.mutsa.homework5.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private final OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        try {
            return orderProductRepository.save(
                    new OrderProduct(
                            orderProduct.getProductName(),
                            orderProduct.getProductPrice(),
                            orderProduct.getProductCnt()
                    )
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<OrderProduct> findById(Long id) {
        try {
            Optional<OrderProduct> orderProduct = orderProductRepository.findById(id);
            if(orderProduct.isPresent()){
                return orderProduct;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
