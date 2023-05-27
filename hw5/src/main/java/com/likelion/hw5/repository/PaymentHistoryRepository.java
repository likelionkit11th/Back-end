package com.likelion.hw5.repository;

import com.likelion.hw5.domain.PaymentHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Long> {

    List<PaymentHistory> findAllByOrderByOrderByPaidAt(Pageable pageable);
}
