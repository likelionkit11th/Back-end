package com.likelion.hw5.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    private Integer orderPrice;

    private LocalDateTime orderedAt;

}
