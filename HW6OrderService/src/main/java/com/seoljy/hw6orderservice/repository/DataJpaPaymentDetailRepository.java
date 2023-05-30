package com.seoljy.hw6orderservice.repository;

import com.seoljy.hw6orderservice.domain.Order;
import com.seoljy.hw6orderservice.domain.PaymentDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataJpaPaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
    @Query(value = "SELECT * FROM payment_detail ORDER BY created_date DESC", nativeQuery = true)
    Page<PaymentDetail> findAllOrderByCreatedDateDesc(Pageable page);
}
