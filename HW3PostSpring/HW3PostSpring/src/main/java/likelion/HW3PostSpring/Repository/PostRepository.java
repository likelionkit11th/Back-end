package likelion.HW3PostSpring.Repository;

import likelion.HW3PostSpring.Domain.Post;
import likelion.HW3PostSpring.Domain.PostDTO;

import java.util.List;

public interface PostRepository {
    Post save(Post post);

    List<Post> findAll();

    Post findById(Long id);
    void deleteById(Long id);

    void update(Long postId, PostDTO postDTO);
}
