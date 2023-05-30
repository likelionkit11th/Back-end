package com.example.orderhw.Repository;

import com.example.orderhw.Domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentLog, Long> {
    @Query("SELECT p FROM PaymentLog p order by p.paymentTime")
    List<PaymentLog> findAllOrderBypaymentTimeDesc();
}
