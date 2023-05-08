package likelion.assignment3.service;

import likelion.assignment3.domain.Post;
import likelion.assignment3.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /* 게시글 작성*/
    public String publish(Post post){
        postRepository.save(post);
        return post.getTitle();
    }

    /* 게시글 전체 조회 */
    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    /* 게시글 조회 By Id */
    public Optional<Post> findOne(Long postId) {return postRepository.findById(postId);}

    /* 게시글 삭제 By Id */
    public void deleteById(Long postId) {postRepository.deleteById(postId);}

    /* 게시글 수정 By Id */
    public void edit(Long postId, String title, String description) {

        /* ifPresent는 Optional 객체가 비어있지 않은 경우 람다식 수행 */
        postRepository.findById(postId)
                .ifPresent(post -> {
                    System.out.println(post.getTitle());
                    post.setTitle(title);
                    post.setDescription(description);
                    postRepository.save(post);
                });


    }

}


