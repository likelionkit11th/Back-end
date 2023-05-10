package likelion.HW3PostSpring.Repository;

import likelion.HW3PostSpring.Domain.Post;
import likelion.HW3PostSpring.Domain.PostDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Repository
public class PostMemoryRepository implements PostRepository{

    private static Map<Long, Post> posts = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Post findById(Long id) {
        return posts.get(id);
    }

    @Override
    public void deleteById(Long id) {
        Post post = posts.get(id);
        posts.remove(id, post);
    }

    @Override
    public void update(Long postId, PostDTO postDTO) {
        Post post = posts.get(postId);
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
    }

}
