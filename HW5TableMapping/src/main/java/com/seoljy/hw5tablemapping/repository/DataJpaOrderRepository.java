package com.seoljy.hw5tablemapping.repository;

import com.seoljy.hw5tablemapping.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaOrderRepository extends JpaRepository<Order, Long> {

}
