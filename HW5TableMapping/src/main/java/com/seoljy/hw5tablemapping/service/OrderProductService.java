package com.seoljy.hw5tablemapping.service;
import com.seoljy.hw5tablemapping.domain.Member;
import com.seoljy.hw5tablemapping.domain.OrderProduct;
import com.seoljy.hw5tablemapping.domain.OrderProductStatus;
import com.seoljy.hw5tablemapping.dto.MemberDTO;
import com.seoljy.hw5tablemapping.dto.OrderProductDTO;
import com.seoljy.hw5tablemapping.repository.DataJpaMemberRepository;
import com.seoljy.hw5tablemapping.repository.DataJpaOrderProductRepository;
import com.seoljy.hw5tablemapping.repository.DataJpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final DataJpaOrderProductRepository dataJpaOrderProductRepository;

    public void createOrderProduct(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = orderProductDTO.toEntity();
        orderProduct.setOrderProductStatus(OrderProductStatus.IN_STOCK);
        dataJpaOrderProductRepository.save(orderProduct);
    }
}
