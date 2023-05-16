package com.seoljy.HW41SpringBoard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends BaseTime {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Builder
    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // 편의 메서드
    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }
}