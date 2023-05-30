package com.example.orderhw.Domain;

import com.example.orderhw.Status.OrderProductStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Table(name = "orderproducts")
@Entity
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderproduct_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order")
    private Order order;

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
//    @Builder.Default()
    private OrderProductStatus productStatus=OrderProductStatus.REQUESTED;

    @Builder
    public OrderProduct(Order order, String productName, Long productPrice, Long productCount, OrderProductStatus productStatus){
        this.order=order;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productCount=productCount;
        this.productStatus=productStatus;
    }

    public void update(Order order, String productName, Long productPrice, Long productCount, OrderProductStatus productStatus){
        this.order=order;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productCount=productCount;
        this.productStatus=productStatus;
    }

    public void setProductStatus(OrderProductStatus productStatus){
        this.productStatus=productStatus;
    }

}
