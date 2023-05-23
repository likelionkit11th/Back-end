package likelion.techit_second.Repository;

import likelion.techit_second.model.BoardDTO;
import likelion.techit_second.model.PostDTO;

import java.util.Collection;

public interface PostRepository {
    PostDTO create(Long boardId, PostDTO dto);
    PostDTO read(Long boardId, Long postId);
    Collection<PostDTO> readAll(Long boardId);
    boolean update(Long boardId,Long postId, PostDTO dto);
    boolean delete(Long boardId,Long postId, String password);
}
