package likelion.practicesql.DTO;

import likelion.practicesql.domain.User;

public record UserDTO(String username, String password) {
    // DTO -> Entity로 변환
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}