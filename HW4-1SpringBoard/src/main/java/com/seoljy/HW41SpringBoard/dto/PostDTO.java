package com.seoljy.HW41SpringBoard.dto;

import com.seoljy.HW41SpringBoard.domain.Post;

public record PostDTO(String title, String description) {
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .description(description)
                .build();
    }
}