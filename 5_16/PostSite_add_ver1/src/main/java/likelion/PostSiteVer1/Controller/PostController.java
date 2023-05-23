package likelion.PostSiteVer1.Controller;


import jakarta.persistence.EntityNotFoundException;
import likelion.PostSiteVer1.Domain.Post;
import likelion.PostSiteVer1.DTO.PostDTO;
import likelion.PostSiteVer1.Service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;



@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class PostController {
    private final PostService postService;
    @GetMapping()
    public String postList(
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = true, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = true, defaultValue = "5") int pagesize,
            Model model,Pageable pageable ) {
        pageable= PageRequest.of(pagenum,pagesize);
        if (keyword.isEmpty()){
            model.addAttribute("posts",postService.findAllPosts(pageable));
        }else {
            model.addAttribute("posts", postService.findbyTitlePost(keyword, pageable));
        }
        return "PostList";
    }
        //글 작성 폼
        @GetMapping("/form")
        public String createPostForm () {
            return "createPostForm";
        }

        //글 작성
        @PostMapping("form")
        public String Postcreate (PostDTO postDTO){
            Long postId = postService.newPost(postDTO);
            return "redirect:/";
        }


//    글 상세 페이지
        @GetMapping("{postId}")
        public String postDetail (@PathVariable("postId") Long postId, Model model){
            Post post = postService.findbyIdPost(postId);
            model.addAttribute("post", post);
            return "PostDetail";
        }
// 글 삭제
        @PostMapping("{postId}")
        public String postDelete (@PathVariable Long postId){
            postService.deletePost(postId);
            return "redirect:/";
        }
//글 수정 폼
        @GetMapping("/{postId}/edit")
        public String postEditForm (@PathVariable("postId") Long postId, Model model){
            Post post = postService.findbyIdPost(postId);
            model.addAttribute("post", post);
            return "editPostForm";
        }

// 글 수정
        @PostMapping("{postId}/edit")
        public String postEdit (@PathVariable("postId") Long postId, PostDTO postDTO){
            postService.editPost(postDTO, postId);
            return "redirect:/";
        }


// 입력 값 예외처리

        @ExceptionHandler(NoSuchElementException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String handleNoSuchElementException (NoSuchElementException e, Model model){
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/");
            return "alert";
        }
}