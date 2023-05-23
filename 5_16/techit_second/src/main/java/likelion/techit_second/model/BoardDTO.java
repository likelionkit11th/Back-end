package likelion.techit_second.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private String name;
    private Long id;

    public BoardDTO() {
    }
}

