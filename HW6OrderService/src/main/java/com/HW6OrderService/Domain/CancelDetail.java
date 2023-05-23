package com.HW6OrderService.Domain;

import com.HW6OrderService.Domain.Status.CancelStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class CancelDetail {
    @Id  @GeneratedValue
    @Column(name = "cancel_id")
    private Long id;
    private String cancelPrice;

    @CreatedDate
    private LocalDate cancelDateTime;

    //취소 상태
    @Enumerated(EnumType.STRING)
    private CancelStatus cancelStatus;

    //주문과 일대다 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
