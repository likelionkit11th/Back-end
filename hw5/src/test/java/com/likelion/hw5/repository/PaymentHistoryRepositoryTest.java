package com.likelion.hw5.repository;

import com.likelion.hw5.domain.PaymentHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@Transactional
public class PaymentHistoryRepositoryTest {

    private final PaymentHistoryRepository paymentHistoryRepository;

    private PaymentHistory payment1;
    private PaymentHistory payment2;
    private PaymentHistory payment3;


    @Autowired
    public PaymentHistoryRepositoryTest(PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    @BeforeEach
    void setUp() {
        // 테스트 데이터 생성
        payment1 = PaymentHistory.builder()

                .paymentMethod(PaymentHistory.PaymentMethod.CARD)
                .paymentAmount(10000)
                .status(PaymentHistory.PaymentStatus.COMPLETED)
                .paidAt(LocalDateTime.now().minusDays(3))
                .build();

        payment2 = PaymentHistory.builder()
                .paymentMethod(PaymentHistory.PaymentMethod.KAKAO_PAY)
                .paymentAmount(20000)
                .status(PaymentHistory.PaymentStatus.COMPLETED)
                .paidAt(LocalDateTime.now())
                .build();

        payment3 = PaymentHistory.builder()
                .paymentMethod(PaymentHistory.PaymentMethod.KAKAO_PAY)
                .paymentAmount(20000)
                .status(PaymentHistory.PaymentStatus.COMPLETED)
                .paidAt(LocalDateTime.now().minusDays(2))
                .build();

        paymentHistoryRepository.save(payment1);
        paymentHistoryRepository.save(payment2);
        paymentHistoryRepository.save(payment3);
    }

    @Test
    @DisplayName("paymentHistoryReposistory 객체 생성")
    void t1() throws Exception {
        //then
        assertThat(paymentHistoryRepository).isNotNull();
    }




    @Test
    @DisplayName("정렬 내림차순 테스트")
    public void testFindAllByOrderByPaidAt() {


        // 정렬된 결제 내역 검색
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "paidAt"));

        Page<PaymentHistory> paymentHistories = paymentHistoryRepository.findAll(pageable);

        // 검증
        assertThat(paymentHistories.getContent().size()).isEqualTo(3);
        // PaidAt이 최신 순으로 정렬되어야 함.
        assertThat(paymentHistories.getContent()).containsExactly(payment2, payment3, payment1);

    }
}