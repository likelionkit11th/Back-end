package com.example.orderhw.Domain;

import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "order_member_name")
    private String orderMemberName;
    @Column(name = "order_price")
    private Long orderPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @CreatedDate
    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy ="orderidvalue")
    @Column(name="orderproudct_id")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    public Order(String orderMemberName, Long orderPrice,Member member) {
        this.orderMemberName = orderMemberName;
        this.orderPrice = orderPrice;
        this.orderStatus=OrderStatus.REQUESTED;
        member.setOrder(this);
        this.orderTime=LocalDateTime.now();
    }

    public Order update(String orderMemberName, Long orderPrice, OrderStatus orderStatus, Member member) {
        this.orderMemberName = orderMemberName;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        member.setOrder(this);
        this.orderTime=LocalDateTime.now();
        return this;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setOrderProduct(OrderProduct orderProduct){
            this.orderProducts.add(orderProduct);
            orderProduct.setOrder(this);
    }

    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
        for(OrderProduct orderProduct : this.orderProducts){
            orderProduct.setProductStatus(OrderProductStatus.RECEIVE_START);

        }
    }

    public void setOrderCancel(CancelLog cancelLog){
        this.orderStatus=OrderStatus.CANCELED;
        for(OrderProduct orderProduct : this.orderProducts){
            orderProduct.setProductStatus(OrderProductStatus.CANCELED);
            orderProduct.setCancelLogId(cancelLog);
        }
    }

    public void setOneOrderCancel(Long orderProductId){
        OrderProduct orderProduct= orderProducts.get(orderProductId.intValue()-1);
        orderProduct.setProductStatus(OrderProductStatus.ERROR);
        orderProducts.set(orderProductId.intValue()-1,orderProduct);
    }
    public Long getMemberId(){
        return this.member.getId();
    }

}
