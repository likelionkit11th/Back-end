package lilelion.assginment4.controller;

import lilelion.assginment4.domain.Post;
import lilelion.assginment4.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "title", required = false) String title, Model model){
        List<Post> posts;
        if(title == null){
            posts = postService.findPosts();
        } else{
            posts = postService.findByTitle(title);
        }
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/form")
    public String form(){
        return "posts/createPostForm";
    }

    @GetMapping("/{postId}")
    public String findOnePost(@PathVariable("postId") Long postId, Model model){
        Post findPost = postService.findOne(postId)
                .orElseThrow(() -> new NoSuchElementException("No Post found with id " + postId));
        model.addAttribute("post", findPost);
        return "posts/postDetail";
    }

    @GetMapping(value = "/{postId}/edit")
    public String editForm(@PathVariable("postId") Long postId, Model model){

        Post findPost = postService.findOne(postId)
                .orElseThrow(() -> new NoSuchElementException("No Post found with id " + postId));
        model.addAttribute("post", findPost);

        return "posts/editPostForm";
    }


}
