package com.mutsa.homework5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cancel_history_id")
    private CancellationHistory cancelHistory;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_cnt")
    private int productCnt;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", columnDefinition = "VARCHAR(20) DEFAULT '판매중'")
    private ProductStatus status;


    public OrderProduct(String productName, int productPrice, int productCnt)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCnt = productCnt;
        this.status = ProductStatus.판매중;
    }
}
