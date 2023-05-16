package com.seoljy.HW41SpringBoard.controller;

import com.seoljy.HW41SpringBoard.domain.Post;
import com.seoljy.HW41SpringBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BoardThymeleafController {
    BoardService boardService;

    @Autowired
    public BoardThymeleafController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String postList(
            @RequestParam(value="title", required = false, defaultValue = "") String title,
            @RequestParam(value="pageNum", required = false, defaultValue = "1") String pageNum,
            @RequestParam(value="pageSize", required = false, defaultValue = "5") String pageSize,
            Model model
    ) {

        List<Post> posts;
        if (!title.equals("")) {
            posts = boardService.searchPostsByTitle(title);
        } else {
            posts = boardService.findPosts();
        }
        model.addAttribute("posts", posts);
        return "postList";
    }

    @GetMapping("/form")
    public String createPostForm() {
        return "createPostForm";
    }

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable("postId") String postId, Model model) {
        try {
            Post post = boardService.findPost(Long.valueOf(postId));
            model.addAttribute("post", post);
        } catch (NoSuchElementException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "postDetail";
    }

    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable("postId") String postId, Model model) {
        Post post = boardService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "editPostForm";
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body("No such element: " + e.getMessage());
    }
}
