package com.mutsa.homework5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cancel_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancellationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancel_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "cancelHistory")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "cancel_price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancel_status", columnDefinition = "VARCHAR(20) DEFAULT '취소대기'")
    private CancelStatus status;

    @Column(name = "cancel_time")
    private LocalDateTime cancel_time;


}
