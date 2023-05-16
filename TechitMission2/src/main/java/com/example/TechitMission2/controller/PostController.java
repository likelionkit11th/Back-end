package com.example.TechitMission2.controller;

import com.example.TechitMission2.model.PostDto;
import com.example.TechitMission2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board/{boardId}/post")
@RequiredArgsConstructor
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @PathVariable("boardId") Long boardId,
            @RequestBody PostDto dto
    ){
        PostDto result = this.postRepository.create(boardId, dto);
        return ResponseEntity.ok(result.passwordMasked());
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId
    ){
        PostDto postDto = this.postRepository.read(boardId, postId);
        if(postDto == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postDto.passwordMasked());
    }


    @GetMapping
    public ResponseEntity<Collection<PostDto>> readPostAll(
            @PathVariable("boardId") Long boardId) {
        Collection<PostDto> postList = this.postRepository.readAll(boardId);
        if(postList == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postList);
    }

    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("id") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDto dto
    ) {
        if (!postRepository.update(boardId, postId, dto))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestParam("password") String password
    ) {
        if(!postRepository.delete(boardId, postId, password))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
