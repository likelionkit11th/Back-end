package com.example.orderhw.Repository;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    void deleteByorderidvalue(Order order);
    List<OrderProduct> findByorderidvalue(Order order);

}
