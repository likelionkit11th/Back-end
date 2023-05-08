package likelion.PostSite.Repository;

import likelion.PostSite.Domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post createPost(Post post);
    Post findByKey(Long id);
    List<Post> findAll();

    void deletePost(Long id);

    Post EditPost(Long id, Post post);
}
