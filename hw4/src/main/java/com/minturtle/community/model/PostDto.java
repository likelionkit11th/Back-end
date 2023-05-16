package com.minturtle.community.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private long boardId;
    private List<String> elementList = new ArrayList<>();


    public PostDto(Long id, String title, String content, String writer, String password, long boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.boardId = boardId;
    }

    public PostDto passwordMasked(){
        return new PostDto(id, title, content, writer, "******", boardId);


    }
}
