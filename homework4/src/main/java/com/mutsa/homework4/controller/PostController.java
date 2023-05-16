package com.mutsa.homework4.controller;

import com.mutsa.homework4.domain.Post;
import com.mutsa.homework4.dto.PostDTO;
import com.mutsa.homework4.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class PostController {
    private final PostService postService;

    //글 전체 리스트 조회 (제목 검색)
//    @GetMapping("/")
//    public String postList(Model model) {
//        List<Post> posts = postService.findPosts();
//        model.addAttribute("posts", posts);
//        return "postList";
//    }
//    @GetMapping("/")
//    public String postList(@RequestParam(required = false) String title, Model model) {
//        Optional<Post> posts;
//        if (title == null || title.isEmpty()) {
//            posts = postService.findPosts();
//        } else {
//            posts = postService.findPostByTitle(title);
//        }
//        model.addAttribute("posts", posts);
//        return "postList";
//    }

    @GetMapping("/")
    public String postList(@RequestParam(required = false) String title, Model model) {
        List<Post> posts;
        if (title == null || title.isEmpty()) {
            posts = postService.findPosts();
        } else {
            posts = postService.findPostByTitle(title);
        }
        model.addAttribute("posts", posts);
        return "postList";
    }


    //글 작성 폼 조회
    @GetMapping("/form")
    public String createForm() {
        return "createPostForm";
    }

    //글 작성
    @PostMapping("/form")
    public String createPost(PostDTO postDTO) {
        postService.create(postDTO);
        return "redirect:/";
    }

    //글 상세 페이지
    @GetMapping("/{postId}")
    public String postDetail(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "postDetail";
    }

    // 글 삭제
    @PostMapping("/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        Post post = postService.findPost(postId);
        postService.deletePost(postId);
        return "redirect:/";
    }

    // 글 수정 폼 조회
    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "editPostForm";
    }

    // 글 수정
    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable("postId") Long postId, PostDTO postDTO) {
        postService.updatePost(postId, postDTO);
        return "redirect:/";
    }

    // 예외 처리
//    @ExceptionHandler(value = {NoSuchElementException.class})
//    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
//        return ResponseEntity.badRequest().body("No such element: " + e.getMessage());
//    }
    @ExceptionHandler(value = {NoSuchElementException.class})
    public String handleNoSuchElementException(NoSuchElementException e, Model model) {
        model.addAttribute("errorMessage", "No such element: " + e.getMessage());
        return "alert";
    }

}
