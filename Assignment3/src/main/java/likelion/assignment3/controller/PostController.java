package likelion.assignment3.controller;

import likelion.assignment3.domain.Post;
import likelion.assignment3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/form")
    public String form(){
        return "posts/createPostForm";
    }

    @PostMapping("/form")
    public String form(PostForm form){
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());

        postService.publish(post);
        return "redirect:/";

    }
    @RequestMapping(value = "/{postId}")
    public String detail(@PathVariable("postId") Long postId, Model model){
        Optional<Post> findPost = postService.findOne(postId);
        /*postDetail에서 post.get().getTitle()로 받아서 Optional 그대로 넘겨줌*/
        model.addAttribute("post", findPost);
        return "posts/postDetail";
    }

    @PostMapping(value = "/{postId}")
    public String delete(@PathVariable("postId") Long postId){
        postService.deleteById(postId);
        return "redirect:/";
    }

    @RequestMapping(value = "/{postId}/edit")
    public String editForm(@PathVariable("postId") Long postId, Model model){

        Optional<Post> findPost = postService.findOne(postId);
        model.addAttribute("post", findPost);

        return "posts/editPostForm";

    }

    @PostMapping(value = "/{postId}/edit")
    public String edit(@PathVariable("postId") Long postId, PostForm form){
        postService.edit(postId, form.getTitle(), form.getDescription());
        return "redirect:/";
    }

}
