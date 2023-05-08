package com.seol.HW3SpringBoard.controller;

import com.seol.HW3SpringBoard.domain.Post;
import com.seol.HW3SpringBoard.service.BoardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class BoardThymeleafController {
    BoardService boardService;

    @Autowired
    public BoardThymeleafController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String postList(Model model) {
        List<Post> posts = boardService.findPosts();
        model.addAttribute("posts", posts);
        return "postList";
    }

    @GetMapping("/form")
    public String createPostForm() {
        return "createPostForm";
    }

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable("postId") String postId, Model model) {
        Optional<Post> post = boardService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable("postId") String postId, Model model) {
        Optional<Post> post = boardService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "editPostForm";
    }

    @ExceptionHandler
    public String errorHandler(Exception e, Model model){
        model.addAttribute("message", e.getMessage());
        model.addAttribute("url", "/");
        return "error";
    }
}
