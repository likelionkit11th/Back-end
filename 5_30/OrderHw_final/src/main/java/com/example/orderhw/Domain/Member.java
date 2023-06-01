package com.example.orderhw.Domain;
import com.example.orderhw.Domain.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@NoArgsConstructor
@Table(name = "members")
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    @Column(name="orders_data")
    private List<Order> orders = new ArrayList<>();

    @Column(name="member_name")
    private String name;

    @Builder
    public Member(String name){
        this.name=name;
    }

    public void update(String name){
        this.name=name;
    }

    public void setAddOrder(){
        this.orders=null;
    }

    public void setOrder(Order order){
            this.orders.add(order);
            order.setMember(this);
    }
    public List<Long> getOrderId(){
        List<Long> orderIdList = new ArrayList<>();
        for(Order order: orders){
            orderIdList.add(order.getId());
        }
        return orderIdList;
    }
}