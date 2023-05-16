package com.mutsa.homework4.service;

import com.mutsa.homework4.domain.Post;
import com.mutsa.homework4.dto.PostDTO;
import com.mutsa.homework4.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class PostService {
    private  final PostRepository postRepository;


    // 글 작성
    @Transactional
    public Long create(PostDTO postDTO) {
        Post post = postDTO.toEntity();
        postRepository.save(post);
        return post.getId();
    }

    // 글 전체 조회
    public List<Post> findPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 제목으로 조회
    public List<Post> findPostByTitle(String title) {
        List<Post> posts = postRepository.findByTitle(title);
        if (posts.isEmpty()) {
            throw new NoSuchElementException("title:" + title + "에 해당하는 게시글이 없습니다.");
        }
        return posts;
    }

    // 글 단건 조회
    public Post findPost(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new NoSuchElementException("id:" + postId + "에 해당하는 게시글이 없습니다."));
    }

    // 글 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 글 수정
    @Transactional
    public void updatePost(Long postId, PostDTO postDTO) {
        postRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException("id"+ postId + "에 해당하는 게시글이 없습니다."))
                .update(postDTO.title(), postDTO.description());
    }



}