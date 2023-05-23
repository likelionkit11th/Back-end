package com.example.orderhw.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "orderproducts")
@Entity
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderproduct_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancel_id")
    private CancelLog CancelLogId;
    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private Long productPrice;

    @Column(name="product_count")
    private Long productCount;

    @Enumerated(EnumType.STRING)
    @Column(name="product_status")
    private OrderProductStatus productStatus;

    @Builder
    public OrderProduct(String productName, Long productPrice, Long productCount){
        this.productName=productName;
        this.productPrice=productPrice;
        this.productCount=productCount;
        this.productStatus=OrderProductStatus.CHECKING;
    }
}
