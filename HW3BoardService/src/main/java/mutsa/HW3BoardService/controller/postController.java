package mutsa.HW3BoardService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutsa.HW3BoardService.domain.Post;
import mutsa.HW3BoardService.domain.PostDTO;
import mutsa.HW3BoardService.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class postController {
    private final PostService postService;

    //게시글 폼 조회
    @GetMapping("/form")
    public String createForm(){
        return "createPostForm";
    }

    //게시글 저장
    @PostMapping("/form")
    public String createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription().replaceAll("\r\n", "<br>"));
        post.setCreatedAt(LocalDateTime.now());
        postService.createPost(post);
        return "redirect:/";
    }

    //게시글 목록 조회
    @GetMapping("/")
    public String postList(@RequestParam(name = "title", required = false) String title,
                @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                @RequestParam(name = "pageSize", defaultValue = "4") int pageSize,
                Model model) {
        Page<Post> posts;
        if (title == null) {
            posts = postService.findPosts(pageNum, pageSize);
        } else {
            List<Post> searchResult = postService.searchTitle(title);
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), searchResult.size());
            posts = new PageImpl<>(searchResult.subList(start, end), pageable, searchResult.size());
        }
        model.addAttribute("posts", posts);
        return "postList";
    }

    //게시글  단건 조회
    @GetMapping("/{postId}")
    public String postDetali(@PathVariable("postId") String postId, Model model) {
        Optional<Post> post = postService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "postDetail";
    }
    
    //게시글 수정 폼 조회
    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable("postId") String postId, Model model) {
        Optional<Post> post = postService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "editPostForm";
    }

    //게시글 수정
    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable("postId") String postId, PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());

        postService.updatePost(Long.valueOf(postId), post);
        return "redirect:/";
    }

    //게시글 삭제
    @PostMapping("/{postId}")
    public String postDelete(@PathVariable("postId") String postId){
        postService.deletePost(Long.valueOf(postId));
        return "redirect:/";
    }

    @ExceptionHandler({IllegalStateException.class, RuntimeException.class})
    public String handleIllegalStateException(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("url", "/");
        return "alert";
    }
}
