package likelion.assignment3.repository;

import likelion.assignment3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SpringDataJpaPostRepositoryTest {
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

//    @Test
//    public void 게시글수정() throws Exception{
//        //given
//        Post post = new Post();
//        post.setTitle("수정 테스트");
////        post.setDescription("수정 테스트중...");
//        postService.publish(post);
//
//        postService.edit(13L, "수정했어용", "수정완료!");
//
//        //when
//        Optional<Post> findPost = postService.findOne(13L);
////
////        //then
//        assertThat(findPost.get().getTitle()).isEqualTo("수정했어용");
//    }

}