package com.seoljy.hw6orderservice.repository;

import com.seoljy.hw6orderservice.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaOrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT u FROM Order u WHERE u.orderName LIKE %:q%")
    Page<Order> findOrdersByUsername(@Param("q") String q, Pageable page);
}
