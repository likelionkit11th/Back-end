package com.seoljy.HW42TechitMisson2.model;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private Long boardId;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, String writer, String password, Long boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.boardId = boardId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public String getPassword() {
        return password;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", boardId=" + boardId +
                '}';
    }

    public PostDto passwordMasked() {
        return new PostDto(
                this.id,
                this.title,
                this.content,
                this.writer,
                "****",
                this.boardId
        );
    }
}
