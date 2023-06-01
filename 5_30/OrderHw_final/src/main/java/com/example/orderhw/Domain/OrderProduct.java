package com.example.orderhw.Domain;

import com.example.orderhw.Status.OrderProductStatus;
import jakarta.persistence.*;
import lombok.*;
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Table(name = "orderproducts")
@Getter
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderproduct_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderidvalue")
    private Order orderidvalue;

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

    public void setOrder(Order order) {
        this.orderidvalue = order;
    }

    @Builder
    public OrderProduct(String productName, Long productPrice, Long productCount){
        this.productName=productName;
        this.productPrice=productPrice;
        this.productCount=productCount;
        this.productStatus=OrderProductStatus.REQUESTED;
    }

    public void update(Order order, String productName, Long productPrice, Long productCount,OrderProductStatus orderProductStatus){
        this.productName=productName;
        this.productPrice=productPrice;
        this.productCount=productCount;
        this.productStatus=productStatus;
        order.setOrderProduct(this);
    }

    public void setCancelLogId(CancelLog cancelLog){
        this.CancelLogId=cancelLog;
    }
    public void setProductStatus(OrderProductStatus productStatus){
        this.productStatus=productStatus;
    }

    public Long getOrderId(){
        return this.orderidvalue.getId();
    }
    public Long getCancelId(){
        if(this.CancelLogId==null){
            return null;
        }
        else{
            return this.CancelLogId.getId();
        }
    }

}
