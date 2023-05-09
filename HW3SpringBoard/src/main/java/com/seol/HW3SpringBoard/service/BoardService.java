package com.seol.HW3SpringBoard.service;

import com.seol.HW3SpringBoard.domain.Post;
import com.seol.HW3SpringBoard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BoardService {
    private  final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public Long createPost(Post post) {
        boardRepository.save(post);
        return post.getId();
    }
    public Optional<Post> findPost(Long postId) {
        return boardRepository.findById(postId);
    }
    public List<Post> findPosts() {
        return boardRepository.findAll();
    }

    public Post updatePost(Long postId, Post post) {
        Post prevPost = boardRepository.findById(postId).get();
        prevPost.setTitle(post.getTitle());
        prevPost.setDescription(post.getDescription());
        return boardRepository.save(prevPost);
    }

    public void deletePost(Long postId) {
        boardRepository.deleteById(postId);
    }
}
