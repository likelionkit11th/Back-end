package com.seoljy.hw5tablemapping.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="orders_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="cancellation_detail_id")
    private CancellationDetail cancellationDetail;

    private String orderProductName;

    private Long orderProductPrice;

    private Long orderProductQuantity;

    @Enumerated(EnumType.STRING)
    private OrderProductStatus orderProductStatus;

    @Builder
    public OrderProduct(String orderProductName, Long orderProductPrice, Long orderProductQuantity) {
        this.orderProductName = orderProductName;
        this.orderProductPrice = orderProductPrice;
        this.orderProductQuantity = orderProductQuantity;
    }

    public void setOrderProductStatus(OrderProductStatus orderProductStatus) {
        this.orderProductStatus = orderProductStatus;
    }
}