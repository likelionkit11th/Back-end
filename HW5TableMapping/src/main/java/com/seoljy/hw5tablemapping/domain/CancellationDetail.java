package com.seoljy.hw5tablemapping.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CancellationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cancellation_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="orders_id")
    private Order order;

    private Long cancellationDetailPrice;

    @Enumerated(EnumType.STRING)
    private CancellationDetailStatus cancellationDetailStatus;

    @CreatedDate
    private LocalDateTime createdDate;

}