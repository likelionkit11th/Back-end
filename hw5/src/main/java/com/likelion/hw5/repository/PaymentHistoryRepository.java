package com.likelion.hw5.repository;

import com.likelion.hw5.domain.PaymentHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Long> {

    List<PaymentHistory> findAllByOrderByPaidAt(Pageable pageable);
}
