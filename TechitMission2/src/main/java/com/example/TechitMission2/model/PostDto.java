package com.example.TechitMission2.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    private Long id;
    private String title;
    private String content;;
    private String writer;
    private String password;
    private Long boardId;

    public PostDto passwordMasked(){
        return new PostDto(
                this.id,
                this.title,
                this.content,
                this.writer,
                "******",
                this.boardId
        );
    }
}
