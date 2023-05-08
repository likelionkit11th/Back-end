package com.seol.HW3SpringBoard.controller;

import com.seol.HW3SpringBoard.domain.Post;
import com.seol.HW3SpringBoard.domain.PostDTO;
import com.seol.HW3SpringBoard.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class BoardRestController {
    private final BoardService boardService;
    @Autowired
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping("/form")
    public String creatPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        boardService.createPost(post);
        log.info("[CREATE] Title: {}   Description: {}", post.getTitle(), post.getDescription());
        return "redirect:/";
    }
    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable("postId") String postId, PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        boardService.updatePost(Long.valueOf(postId), post);
        log.info("[UPDATE] UPDATE Id: {}   UPDATE Title: {}   UPDATE Description: {}", postId, post.getTitle(), post.getDescription());
        return "redirect:/" + postId;
    }
    @PostMapping("/{postId}")
    public String deletePost(@PathVariable("postId") String postId) {
        boardService.deletePost(Long.valueOf(postId));
        log.info("[DELETE] DELETE Id: {}", postId);
        return "redirect:/";
    }
}
