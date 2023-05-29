package com.seoljy.hw6orderservice.service;

import com.seoljy.hw6orderservice.domain.Order;
import com.seoljy.hw6orderservice.domain.PaymentDetail;
import com.seoljy.hw6orderservice.repository.DataJpaOrderRepository;
import com.seoljy.hw6orderservice.repository.DataJpaPaymentDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentDetailService {

    private DataJpaPaymentDetailRepository dataJpaPaymentDetailRepository;

    public List<PaymentDetail> findAll(Pageable pageable){
        return dataJpaPaymentDetailRepository.findAllOrderByCreatedDateDesc(pageable).toList();
    }

}
