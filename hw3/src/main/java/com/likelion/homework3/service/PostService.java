package com.likelion.homework3.service;

import com.likelion.homework3.controller.PostController;
import com.likelion.homework3.domain.Post;
import com.likelion.homework3.dto.PostDTO;
import com.likelion.homework3.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;


    @Transactional(readOnly = true)
    public List<Post> getPostList(@NonNull Map<String, String> paramMap){

        if(paramMap.containsKey("title")){
            return postRepository.findByTitleOrderByCreatedAtDesc(paramMap.get("title"));
        }
        return postRepository.findAllByOrderByCreatedAtDesc();

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
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

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
