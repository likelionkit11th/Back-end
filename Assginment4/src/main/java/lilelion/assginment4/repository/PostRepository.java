package lilelion.assginment4.repository;

import lilelion.assginment4.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long Id);
    Optional<List<Post>> findByTitle(String title);
    void deleteById(Long Id);
    Optional<List<Post>> findAllByOrderByCreatedDateDesc();
}

