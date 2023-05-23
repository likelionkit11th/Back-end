package com.example.orderhw.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@Table(name = "cancels")
@Entity
public class CancelLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cancel_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderId;
    @Column(name="cancel_price")
    private Long cancelPrice;

    @Enumerated(EnumType.STRING)
    @Column(name="cancel_status")
    private CancelLogStatus status;

    @CreatedDate
    @Column(name="cancel_time")
    private LocalDateTime cancelTime;
}
