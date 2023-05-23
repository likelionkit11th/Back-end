package com.likelion.hw5.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private Integer unitPrice; // 개당 가격

}
