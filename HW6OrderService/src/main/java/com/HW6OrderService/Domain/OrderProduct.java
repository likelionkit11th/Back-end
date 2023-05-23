package com.HW6OrderService.Domain;

import com.HW6OrderService.Domain.Status.OrderProductStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "orders_product")
public class OrderProduct {
    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;
    private String name;
    private String price;
    private String amount;
    private String status;

    //주문 상품 상태
    @Enumerated(EnumType.STRING)
    private OrderProductStatus orderProductStatus;

    //주문, 취소 내역에 대해 각각 다대일 매핑
    @JoinColumn(name = "cancel_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CancelDetail cancelDetail;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
