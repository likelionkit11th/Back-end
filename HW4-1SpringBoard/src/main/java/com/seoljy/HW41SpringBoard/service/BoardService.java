package com.seoljy.HW41SpringBoard.service;

import com.seoljy.HW41SpringBoard.domain.Post;
import com.seoljy.HW41SpringBoard.dto.PostDTO;
import com.seoljy.HW41SpringBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private  final BoardRepository boardRepository;

    @Transactional
    public Long createPost(PostDTO postDTO) {
        Post post = postDTO.toEntity();
        boardRepository.save(post);
        return post.getId();
    }

    public Post findPost(Long postId) {
        return boardRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException("id"+ postId + "에 해당하는 게시물이 없습니다."));
    }

    public Page<Post> searchPostsByTitle(String title, Pageable pageable) {
        return boardRepository.findByTitleContaining(title, pageable);
    }

    public Page<Post> findPosts(Pageable pageable) {
        return boardRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Transactional
    public void updatePost(Long postId, PostDTO postDTO) {
        boardRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException("id"+ postId + "에 해당하는 게시물이 없습니다."))
                .update(postDTO.title(), postDTO.description());
    }

    @Transactional
    public void deletePost(Long postId) {
        boardRepository.deleteById(postId);
    }
}
