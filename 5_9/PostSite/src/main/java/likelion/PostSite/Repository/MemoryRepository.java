package likelion.PostSite.Repository;

import likelion.PostSite.Domain.Post;
import likelion.PostSite.Domain.PostDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Getter
@Setter
@Repository
public class MemoryRepository implements PostRepository {
    //User를 담는 저장소로 HashMap 사용
    private static Map<Long, Post> posts = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Post createPost(Post post) {
        post.setId(++sequence);
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public Post findByKey(Long id) {
        return posts.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public void deletePost(Long id) {
        posts.remove(id);
    }

    public Post EditPost(Long id ,Post post) {
        posts.put(id,post);
        return post;
    }
}