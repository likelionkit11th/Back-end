package com.likelion.hw1;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class User {

    @Builder
    public User(String username, String password, String name, String birthDate, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }

    private String username;
    private String password;
    private String name;
    private String birthDate;
    private String email;
    
    
    
}
