package likelion.assignment3;

import likelion.assignment3.repository.PostRepository;
import likelion.assignment3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final PostRepository postRepository;

    @Autowired
    public SpringConfig(PostRepository postRepository) {this.postRepository = postRepository;};

    @Bean
    public PostService postService() {return new PostService(postRepository);}

}
