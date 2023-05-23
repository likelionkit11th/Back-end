package com.likelion.hw5.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class UserEntity {
    public UserEntity(String name) {
        this.name = name;
    }

    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String name;
}
