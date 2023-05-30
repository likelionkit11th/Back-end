package com.seoljy.hw6orderservice.repository;

import com.seoljy.hw6orderservice.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaOrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
