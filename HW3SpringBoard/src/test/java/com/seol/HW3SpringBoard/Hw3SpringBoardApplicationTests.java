package com.seol.HW3SpringBoard;

import com.seol.HW3SpringBoard.domain.Post;
import com.seol.HW3SpringBoard.repository.BoardRepository;
import com.seol.HW3SpringBoard.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class Hw3SpringBoardApplicationTests {

	@Autowired
	BoardService boardService;
	@Autowired
	BoardRepository boardRepository;

	@Test
	void 전체post조회() {
		// given
		Post post1 = new Post();
		post1.setTitle("title1");
		post1.setDescription("description1");

		Post post2 = new Post();
		post2.setTitle("title2");
		post2.setDescription("description2");

		Post post3 = new Post();
		post3.setTitle("title3");
		post3.setDescription("description3");

		Long saveId1 = boardService.createPost(post1);
		Long saveId2 = boardService.createPost(post2);
		Long saveId3 = boardService.createPost(post3);

		// when
		List<Post> posts = boardService.findPosts();

		// then
		assertThat(post1.getTitle()).isEqualTo(posts.get(0).getTitle());
		assertThat(post1.getDescription()).isEqualTo(posts.get(0).getDescription());

		assertThat(post2.getTitle()).isEqualTo(posts.get(1).getTitle());
		assertThat(post2.getDescription()).isEqualTo(posts.get(1).getDescription());

		assertThat(post3.getTitle()).isEqualTo(posts.get(2).getTitle());
		assertThat(post3.getDescription()).isEqualTo(posts.get(2).getDescription());
	}

	@Test
	void post조회() {
		// given
		Post post1 = new Post();
		post1.setTitle("title1");
		post1.setDescription("description1");

		Post post2 = new Post();
		post2.setTitle("title2");
		post2.setDescription("description2");

		Post post3 = new Post();
		post3.setTitle("title3");
		post3.setDescription("description3");

		Long saveId1 = boardService.createPost(post1);
		Long saveId2 = boardService.createPost(post2);
		Long saveId3 = boardService.createPost(post3);

		// when
		Optional<Post> post = boardService.findPost(saveId2);

		// then
		assertThat(post2.getTitle()).isEqualTo(post.get().getTitle());
		assertThat(post2.getDescription()).isEqualTo(post.get().getDescription());
	}

	@Test
	void post작성() {
		// given
		Post post = new Post();
		post.setTitle("title");
		post.setDescription("description");

		// when
		Long saveId = boardService.createPost(post);

		// then
		Optional<Post> findPost = boardService.findPost(saveId);
		assertThat(post.getTitle()).isEqualTo(findPost.get().getTitle());
		assertThat(post.getDescription()).isEqualTo(findPost.get().getDescription());
	}

	@Test
	void post수정() {
		// given
		Post post1 = new Post();
		post1.setTitle("title1");
		post1.setDescription("description1");

		Post post2 = new Post();
		post2.setTitle("title2");
		post2.setDescription("description2");

		Post post3 = new Post();
		post3.setTitle("title3");
		post3.setDescription("description3");

		Long saveId1 = boardService.createPost(post1);
		Long saveId2 = boardService.createPost(post2);
		Long saveId3 = boardService.createPost(post3);

		// when
		Post updateData = new Post();
		updateData.setTitle("title4");
		updateData.setDescription("description4");

		boardService.updatePost(saveId2, updateData);

		// then
		Optional<Post> findPost = boardService.findPost(saveId2);
		assertThat(findPost.get().getTitle()).isEqualTo(updateData.getTitle());
		assertThat(findPost.get().getDescription()).isEqualTo(updateData.getDescription());
	}

	@Test
	void post삭제() {
		// given
		Post post1 = new Post();
		post1.setTitle("title1");
		post1.setDescription("description1");

		Post post2 = new Post();
		post2.setTitle("title2");
		post2.setDescription("description2");

		Post post3 = new Post();
		post3.setTitle("title3");
		post3.setDescription("description3");

		Long saveId1 = boardService.createPost(post1);
		Long saveId2 = boardService.createPost(post2);
		Long saveId3 = boardService.createPost(post3);

		// when
		boardService.deletePost(saveId2);

		// then
		List<Post> posts = boardService.findPosts();
		assertThat(posts.size()).isEqualTo(2);
	}
}
