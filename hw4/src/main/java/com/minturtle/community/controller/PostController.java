package com.minturtle.community.controller;


import com.minturtle.community.model.BoardDto;
import com.minturtle.community.model.PostDto;
import com.minturtle.community.repository.PostRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board/{boardId}/post")
@Slf4j
@RequiredArgsConstructor
public class PostController {


    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<PostDto> createPost( @PathVariable Long boardId, @RequestBody PostDto dto){
        return ResponseEntity.ok(postRepository.create(boardId, dto).passwordMasked());
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(@PathVariable Long postId, @PathVariable Long boardId){
        final PostDto postDto = postRepository.read(boardId, postId);
        if(postDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity<Collection<PostDto>> readPostAll(@PathVariable Long boardId){
        final Collection<PostDto> postDtos = postRepository.readAll(boardId);
        if(postDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(postDtos);
    }


    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long boardId, @PathVariable Long postId, @RequestBody PostDto dto){
        if(!postRepository.update(boardId, postId, dto)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long boardId, @PathVariable Long postId, @RequestParam String password){
        if(!postRepository.delete(boardId, postId, password)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

}
