package com.example.orderhw.Repository;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.PaymentLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentLog, Long> {
    @Query("SELECT p FROM PaymentLog p order by p.paymentTime desc")
    Page<PaymentLog> findAllOrderBypaymentTimeDesc(Pageable pageable);

    Optional<PaymentLog> findByOrderId(Order order);
}
