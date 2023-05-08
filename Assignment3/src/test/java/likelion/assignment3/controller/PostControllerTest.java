package likelion.assignment3.controller;

import likelion.assignment3.domain.Post;
import likelion.assignment3.repository.PostRepository;
import likelion.assignment3.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostControllerTest {
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

    @Test
    public void findOne() throws Exception{
        //given
        Post post = new Post();
        post.setTitle("테스트");
        postRepository.save(post);
        //when

        /* 이거 자동으로 중가하는데 테스트를 어떻게 해야할지 궁금해용*/
        Post findPost = postService.findOne(8L).get();

        //then
        assertThat(post.getId()).isEqualTo(findPost.getId());

    }

}