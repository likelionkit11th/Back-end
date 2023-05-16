package com.mutsa.homework4.dto;

import com.mutsa.homework4.domain.Post;

public record PostDTO(String title, String description) {
    // DTO -> Entity로 변환
    public Post toEntity() {
        return Post.builder().title(title).description(description).build();

    }

}
