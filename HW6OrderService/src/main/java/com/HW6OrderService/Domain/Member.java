package com.HW6OrderService.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Member {
    @Id  @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    //주문과 양방향 연관관계, 1대다
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public  Member(String name) {
        this.name = name;
    }
}
