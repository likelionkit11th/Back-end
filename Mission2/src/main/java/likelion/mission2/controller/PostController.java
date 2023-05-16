package likelion.mission2.controller;

import likelion.mission2.model.PostDto;
import likelion.mission2.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@PathVariable("boardId") Long boardId, @RequestBody PostDto dto){
        return null;
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId){
        PostDto postDto = this.postRepository.read(boardId, postId);
        if(postDto == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postDto.passwordMasked());

    }

    @GetMapping
    public ResponseEntity<Collection<PostDto>> readPostAll(@PathVariable("boardId") Long boardId){
        Collection<PostDto> postList = this.postRepository.readAll(boardId);
        if(postList == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postList);

    }

    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("boardID") Long boardId, @PathVariable("postId") Long postId,@RequestBody PostDto dto){
        if(!postRepository.update(boardId, postId, dto));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("boardID") Long boardId, @PathVariable("postId") Long postId, @RequestParam("password") String password){
        if(!postRepository.delete(boardId, postId, password))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }


}
