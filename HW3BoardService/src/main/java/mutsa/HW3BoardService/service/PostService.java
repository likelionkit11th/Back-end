package mutsa.HW3BoardService.service;

import lombok.RequiredArgsConstructor;
import mutsa.HW3BoardService.domain.Post;
import mutsa.HW3BoardService.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Long createPost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public Page<Post> findPosts(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        return postRepository.findAll(pageable);
    }

    public Optional<Post> findPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        validatePostExist(post);
        return post;
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    public void updatePost(Long postId, Post updatedPost) {
        Optional<Post> post = postRepository.findById(postId);
        validatePostExist(post);
        post.get().setId(postId);
        post.get().setTitle(updatedPost.getTitle());
        post.get().setDescription(updatedPost.getDescription());
        postRepository.save(post.get());
    }

    public List<Post> searchTitle(String title) {
        List<Post> posts = postRepository.findByTitleContainingOrderByCreatedAtDesc(title);
        if (posts.isEmpty()) {
            throw new RuntimeException("검색 결과가 없습니다.");
        }
        return posts;
    }

    private static void validatePostExist(Optional<Post> post) {
        if (post.isEmpty()) {
            throw new IllegalStateException("게시글을 찾을 수 없습니다.");
        }
    }

}
