package com.mutsa.HW4UserRegisterDatabase.DTO;

import com.mutsa.HW4UserRegisterDatabase.Domain.User;

public record UserDTO(String username, String password) {
    // DTO -> Entity로 변환
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}

