package com.likelion.hw5.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CancelHistory {

    @Id @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;


    private Integer cancelPrice;

    private LocalDateTime canceledAt;

}
