package com.example.orderhw.Service;

import com.example.orderhw.DTO.OrderProductDTO;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional//(readOnly = true)
public class OrderProductService {
    private final OrderProductRepository orderproductRepository;
    public Long Create(OrderProductDTO orderproductDTO){
        OrderProduct orderproduct= orderproductDTO.toEntity();
        orderproductRepository.save(orderproduct);
        return orderproduct.getId();
    }
}
