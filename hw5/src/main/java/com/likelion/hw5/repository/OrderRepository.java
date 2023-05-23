package com.likelion.hw5.repository;

import com.likelion.hw5.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
}
