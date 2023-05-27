package com.likelion.hw5.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users")
public class User {
    public User(String name) {
        this.name = name;
    }

    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String name;
}
