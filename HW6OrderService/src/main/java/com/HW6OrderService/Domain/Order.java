package com.HW6OrderService.Domain;

import com.HW6OrderService.Domain.Status.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id  @GeneratedValue
    @Column(name = "orders_id")
    private Long id;
    private String orderPrice;

    @CreatedDate
    private LocalDate orderDateTime;
    
    //주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //회원과 다대일 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //빌더 생성자
    @Builder
    public Order(String orderPrice, OrderStatus orderStatus, Member member) {
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.member = member;
    }
}
