package com.likelion.hw5.repository;

import com.likelion.hw5.domain.PaymentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Long> {

    Page<PaymentHistory> findAll(Pageable pageable);
}
