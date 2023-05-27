package com.likelion.hw5.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@Transactional
public class PaymentHistoryRepositoryTest {

    private final PaymentHistoryRepository paymentHistoryRepository;


    @Autowired
    public PaymentHistoryRepositoryTest(PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
    }


    @Test
    @DisplayName("paymentHistoryReposistory 객체 생성")
    void t1() throws Exception {
        //then
        assertThat(paymentHistoryRepository).isNotNull();
    }
}