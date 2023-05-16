package likelion.mission2.repository;

import likelion.mission2.model.PostDto;

import java.util.Collection;

public interface PostRepository {
    PostDto create(Long boardId, PostDto dto);
    PostDto read(Long boardId, Long postId);
    Collection<PostDto> readAll(Long boardId);
    boolean update(Long boardId, Long PostId, PostDto dto);
    boolean delete(Long boardId, Long PostId, String password);
}
