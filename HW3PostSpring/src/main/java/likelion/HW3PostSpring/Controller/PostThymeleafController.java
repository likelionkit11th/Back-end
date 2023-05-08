package likelion.HW3PostSpring.Controller;

import likelion.HW3PostSpring.Domain.Post;
import likelion.HW3PostSpring.Domain.PostDTO;
import likelion.HW3PostSpring.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PostThymeleafController {

    private final PostService postService;

    @Autowired
    public PostThymeleafController(PostService postService) {
        this.postService = postService;
    }


    //글 전체 리스트 조회
    @GetMapping("/")
    public String postList(Model model) {
        List<Post> posts = postService.findPosts();
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
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());

        log.info("title = {}, description={}", post.getTitle(), post.getDescription());

        postService.create(post);
        return "redirect:/";
    }

    //글 상세 페이지
    @GetMapping("/{postId}")
    public String postDetail(@PathVariable("postId") String postId, Model model) {
        Post post = postService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "postDetail";
    }

    // 글 삭제
    @PostMapping("/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }

    // 글 수정 폼 조회
    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable("postId") String postId, Model model) {
        Post post = postService.findPost(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "editPostForm";
    }

    // 글 수정
    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable("postId") Long postId, PostDTO postDTO) {
        postService.updatePost(postId, postDTO);
        return "redirect:/";
    }


}
