package likelion.PostSite.Controller;


import likelion.PostSite.Domain.Post;
import likelion.PostSite.Domain.PostDTO;
import likelion.PostSite.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    //    글 전체 리스트
    @GetMapping()
    public String postList(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "PostList";
    }

//    글 작성 폼 조회
    @GetMapping("/form")
    public String createPostForm() {
        return "createPostForm";
    }

    //글 작성
    @PostMapping("/form")
    public String createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        log.info("Title = {} , Description = {}", post.getTitle(), post.getDescription());

        postService.newPost(post);
        return "redirect:/";
    }



//    글 상세 페이지
    @GetMapping("/{postId}")
    public String postDetail(@PathVariable("postId") String postId, Model model) {
        Post post = postService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "PostDetail";
    }
//
// 글 삭제
    @PostMapping("/{postId}")
    public String postDelete(@PathVariable("postId") String postId) {
        postService.deletePost(Long.valueOf(postId));
        return "redirect:/";
    }

//  글 수정 폼 조회
    @GetMapping("/{postId}/edit")
    public String postEditForm(@PathVariable("postId") String postId, Model model) {
        Post post = postService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "editPostForm";
    }
//
// 글 수정
    @PostMapping("/{postId}/edit")
    public String postEdit(@PathVariable("postId") String postId,PostDTO postDTO) {
        Post post= new Post();
        post.setId(Long.valueOf(postId));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        log.info("Title = {} , Description = {}", post.getTitle(), post.getDescription());

        postService.editPost(Long.valueOf(postId) , post);
        return "redirect:/";
    }

//    오류 처리?
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleDuplicate(IllegalStateException exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
//    }
}