package com.likelion.hw5.repository;

import com.likelion.hw5.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

    @Query("SELECT o FROM Order o WHERE o.user.name LIKE %:username%")
    Page<Order> findOrdersByUsername(@Param("username") String username, Pageable page);
}
