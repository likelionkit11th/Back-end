package com.example.orderhw.Repository;

import com.example.orderhw.Domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.orderMemberName LIKE %?1%")
    Page<Order> findByOrderMemberNameLike(String orderMemberName, Pageable pageable);

}
