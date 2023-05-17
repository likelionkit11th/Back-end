package likelion.techit_second.Controller;

import likelion.techit_second.Repository.PostRepository;
import likelion.techit_second.model.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostRepository postRepository;
    public PostController(@Autowired PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(
            @PathVariable("boardId") Long boardId,
            @RequestBody PostDTO dto){
        PostDTO result = this.postRepository.create(boardId, dto);
        return ResponseEntity.ok(result.passwordMasked());
    }

    @GetMapping({"postId"})
    public ResponseEntity<PostDTO> readPost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId){
        PostDTO postDTO = this.postRepository.read(boardId, postId);
        if(postDTO ==null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postDTO.passwordMasked());
    }
    @GetMapping
    public ResponseEntity<Collection<PostDTO>> readPostAll(
            @PathVariable("boardId") Long boardId){
        Collection<PostDTO> postList = this.postRepository.readAll(boardId);
        if(postList == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postList );
    }
    @PutMapping({"postId"})
    public ResponseEntity<?> updatePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDTO dto){
        if(!postRepository.update(boardId,postId,dto)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping({"postId"})
    public ResponseEntity<?> deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestParam("password") String password){
        if(!postRepository.delete(boardId,postId, password)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
