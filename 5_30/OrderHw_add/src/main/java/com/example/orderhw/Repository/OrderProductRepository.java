package com.example.orderhw.Repository;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    void deleteByOrder(Order order);
    @Query(value = "SELECT product FROM OrderProduct product ORDER BY product.productName DESC", nativeQuery = true)
    Optional<OrderProduct> findByOrder(Order order);
}
