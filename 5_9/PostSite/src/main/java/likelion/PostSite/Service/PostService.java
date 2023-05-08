package likelion.PostSite.Service;

import likelion.PostSite.Domain.Post;
import likelion.PostSite.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private  final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long newPost(Post post) {
        postRepository.createPost(post);

        return post.getId();
    }


    public Post findPost(Long postId) {
        return postRepository.findByKey(postId);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public void deletePost(Long postId){
        postRepository.deletePost(postId);
    }

    public Long editPost(Long postId, Post post) {
        postRepository.EditPost(postId,post);
        return post.getId();
    }
}