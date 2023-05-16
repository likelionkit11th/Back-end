package com.seoljy.HW41SpringBoard.controller;
import com.seoljy.HW41SpringBoard.domain.Post;
import com.seoljy.HW41SpringBoard.dto.PostDTO;
import com.seoljy.HW41SpringBoard.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

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
        boardService.createPost(postDTO);
        log.info("[CREATE]");
        return "redirect:/";
    }

    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable("postId") String postId, PostDTO postDTO) {
        boardService.updatePost(Long.valueOf(postId), postDTO);
        log.info("[UPDATE]");
        return "redirect:/" + postId;
    }

    @PostMapping("/{postId}")
    public String deletePost(@PathVariable("postId") String postId) {
        boardService.deletePost(Long.valueOf(postId));
        log.info("[DELETE] DELETE Id: {}", postId);
        return "redirect:/";
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body("No such element: " + e.getMessage());
    }
}
