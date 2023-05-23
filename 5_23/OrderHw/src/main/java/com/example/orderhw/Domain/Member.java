package com.example.orderhw.Domain;
import com.example.orderhw.Domain.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "members")
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    private String name;

    public void addOrder(Order order){
        orders.add(order);
        order.setOrder(this);
    }

    public void removeOrder(Order order){
        orders.remove(order);
        order.setOrder(null);
    }
    @Builder
    public Member(String name){
        this.name=name;
    }
}
