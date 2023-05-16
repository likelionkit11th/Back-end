package lilelion.assginment4.controller;

import lilelion.assginment4.dto.PostDTO;
import lilelion.assginment4.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private static final Logger logger = LoggerFactory.getLogger(PostApiController.class);
    private final PostService postService;

    // create
    @PostMapping("/form")
    public ResponseEntity<?> publishPost(PostDTO postDTO){
        Long postId = postService.publish(postDTO);
        logger.info("Created new post with ID: {}", postId);
        return redirect(postId);
    }

    // delete
    @PostMapping(value = "/{postId}")
    public ResponseEntity<?> delete(@PathVariable("postId") Long postId){
        postService.deleteById(postId);
        logger.info("Deleted post with ID: {}", postId);
        return redirect();
    }

    // put
    @PostMapping(value = "/{postId}/edit")
    public ResponseEntity<?> edit(@PathVariable("postId") Long postId, PostDTO postDTO){
        postService.edit(postId, postDTO);
        logger.info("Edited post with ID: {}", postId);
        return redirect();
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ModelAndView handleNoSuchElementException(NoSuchElementException e) {
        logger.error("Error occurred: {}", e.getMessage());
        ModelAndView mav = new ModelAndView("alert");
        mav.addObject("message", "No such element: " + e.getMessage());
        mav.addObject("url", "/");
        return mav;
    }

    public ResponseEntity<?> redirect(Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"+id));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public ResponseEntity<?> redirect(){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
