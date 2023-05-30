package likelion.assignment6.repository;

import likelion.assignment6.entity.PaymentDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
    Page<PaymentDetail> findAllByOrderByPaymentDateDesc(Pageable pageable);
}
