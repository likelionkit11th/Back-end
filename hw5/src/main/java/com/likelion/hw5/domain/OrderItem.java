package com.likelion.hw5.domain;


import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private CancelHistory cancelHistory; // 취소 내역

    private String name;

    private Integer unitPrice; // 개당 가격

    private Integer stockQuantity; // 수량




    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    public static enum ItemStatus{
        ORDERED, CANCELED, COMPLETED
    }
}
