package likelion.techit_second.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private List<String> elementList = new ArrayList<>();
    private String writer;
    private String password;
    private  Long boardId;

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", boardId=" + boardId +
                '}';
    }
    public PostDTO(){

    }

    public PostDTO(
            Long id,
            String title,
            String content,
            String writer,
            String password,
            Long boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.boardId = boardId;
    }
    public PostDTO passwordMasked(){
        return new PostDTO(
                this.id,
                this.title,
                this.content,
                this.writer,
                "******",
                this.boardId);
    }
}
