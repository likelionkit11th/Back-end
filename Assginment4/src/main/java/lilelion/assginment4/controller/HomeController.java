package lilelion.assginment4.controller;

import lilelion.assginment4.domain.Post;
import lilelion.assginment4.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "title", required = false) String title, Model model){
        List<Post> posts;
        if(title == null){
            posts = postService.findPosts();
            logger.info("Fetching all posts");
        } else{
            posts = postService.findByTitle(title);
            logger.info("Fetching posts with title: {}", title);
        }
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/form")
    public String form(){
        logger.info("Opening post creation form");
        return "posts/createPostForm";
    }

    @GetMapping("/{postId}")
    public String findOnePost(@PathVariable("postId") Long postId, Model model){
        Post findPost = postService.findOne(postId)
                .orElseThrow(() -> new NoSuchElementException("No Post found with id " + postId));
        logger.info("Fetching post with id: {}", postId);
        model.addAttribute("post", findPost);
        return "posts/postDetail";
    }

    @GetMapping(value = "/{postId}/edit")
    public String editForm(@PathVariable("postId") Long postId, Model model){
        Post findPost = postService.findOne(postId)
                .orElseThrow(() -> new NoSuchElementException("No Post found with id " + postId));
        logger.info("Fetching post with id: {} for edit", postId);
        model.addAttribute("post", findPost);
        return "posts/editPostForm";
    }
}
