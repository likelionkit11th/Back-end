package com.likelion.hw5.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Item {

    public Item(String name, Integer unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    @Id @GeneratedValue
    private Long id;

    private String name;

    private Integer unitPrice; // 개당 가격

}
