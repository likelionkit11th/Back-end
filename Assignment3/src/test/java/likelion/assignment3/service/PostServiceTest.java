package likelion.assignment3.service;

import likelion.assignment3.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

//    @Test
//    public void 글작성() throws Exception{
//        //Given
//        Post post = new Post();
//        post.setTitle("안녕");
//        //When
//        String saveTitle = postService.publish(post);
//        //Then
//        Post findPost = postRepository.findByTitle(saveTitle).get();
//        assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
//    }

}