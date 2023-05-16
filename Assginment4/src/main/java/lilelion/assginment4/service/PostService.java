package lilelion.assginment4.service;

import lilelion.assginment4.domain.Post;
import lilelion.assginment4.dto.PostDTO;
import lilelion.assginment4.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /* 게시글 작성*/
    @Transactional
    public Long publish(PostDTO postDTO){
        Post post = postDTO.toEntity();
        postRepository.save(post);
        return post.getId();
    }

    /* 게시글 전체 조회 */
    public List<Post> findPosts(){
        return postRepository.findAllByOrderByCreatedDateDesc().get();
    }

    /* 게시글 조회 By Id */
    public Optional<Post> findOne(Long postId) {return postRepository.findById(postId);}

    /* 게시글 조회 By Title*/
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title).orElseThrow(() -> new NoSuchElementException("No Post found with postTitle " + title));
    }

    /* 게시글 삭제 By Id */
    @Transactional
    public void deleteById(Long postId) {postRepository.deleteById(postId);}

    /* 게시글 수정 By Id */
    @Transactional
    public void edit(Long postId, PostDTO postDTO) {

        postRepository
                .findById(postId)
                .orElseThrow(() -> new NoSuchElementException("No Post found with postId " + postId))
                .update(postDTO.title(), postDTO.content());

    }

}


