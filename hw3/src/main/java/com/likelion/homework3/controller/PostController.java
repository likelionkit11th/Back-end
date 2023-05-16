package com.likelion.homework3.controller;


import com.likelion.homework3.domain.Post;
import com.likelion.homework3.dto.PostDTO;
import com.likelion.homework3.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @GetMapping("/")
    public String getList(@RequestParam(required = false) HashMap<String,String> paramMap, Model model){

        List<Post> postList = postService.getPostList(paramMap);
        model.addAttribute("posts", postList);

        return "postList";
    }

    @GetMapping("/form")
    public String getForm(){
        return "createPostForm";
    }


    @PostMapping("/form")
    public String addPost(PostDTO postDTO){
        postService.save(postDTO);
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String getPostDetail(@PathVariable(name = "postId") Long id, Model model){
        final Post findPost = postService.findPostById(id);
        model.addAttribute("post", findPost);
        return "postDetail";
    }


    @PostMapping("/{postId}")
    public String deletePost(@PathVariable(name = "postId") Long id){
        postService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/{postId}/edit")
    public String getEditForm(@PathVariable(name = "postId") Long id, Model model){

        Post post = postService.findPostById(id);

        model.addAttribute("post", post);
        return "editPostForm";
    }


    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable(name = "postId") Long id, PostDTO modifiedPost){
        postService.modify(id, modifiedPost);
        return "redirect:/";
    }


    @ExceptionHandler(value = {EntityNotFoundException.class})
    public String errorPageHandler(Exception e, Model model){

        model.addAttribute("message", e.getMessage());
        model.addAttribute("url", "/");

        return "errorPage";
    }


    @Data
    public static class SearchRequest{
        private String title;

    }
}
