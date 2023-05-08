package likelion.assignment3.repository;

import likelion.assignment3.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long Id);
    Optional<Post> findByTitle(String title);
    void deleteById(Long Id);
    List<Post> findAll();
}

