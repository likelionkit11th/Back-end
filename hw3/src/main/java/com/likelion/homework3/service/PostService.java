package com.likelion.homework3.service;

import com.likelion.homework3.domain.Post;
import com.likelion.homework3.dto.PostDTO;
import com.likelion.homework3.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;


    @Transactional(readOnly = true)
    public List<Post> getPostList(){
        List<Post> posts = postRepository.findAll();

        return posts;
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long id) throws EntityNotFoundException {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글을 찾을 수 없습니다."));

        return findPost;
    }

    public void save(PostDTO dto){
        Post newPost = Post.builder()
                .title(dto.getTitle())
                .description(dto.getDescription()).build();

        postRepository.save(newPost);
    }




    public void delete(Long id){
        postRepository.deleteById(id);
    }


    public void modify(Long id, PostDTO modifiedPost) throws EntityNotFoundException {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글을 찾을 수 없습니다."));

        findPost.setTitle(modifiedPost.getTitle());
        findPost.setDescription(modifiedPost.getDescription());
    }
}
