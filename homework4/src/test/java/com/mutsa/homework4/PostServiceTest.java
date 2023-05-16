package com.mutsa.homework4;

import com.mutsa.homework4.domain.Post;
import com.mutsa.homework4.dto.PostDTO;
import com.mutsa.homework4.repository.PostRepository;
import com.mutsa.homework4.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceTest {
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository);
    }

    @Test
    @DisplayName("게시글을 생성할 수 있다.")
    void createPost() {
        // Given
        PostDTO postDTO = new PostDTO("제목", "내용");
        Post post = postDTO.toEntity();
        when(postRepository.save(any())).thenReturn(post);

        // When
        Long postId = postService.create(postDTO);

        // Then
        assertNotNull(postId);
        verify(postRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("전체 게시글을 조회할 수 있다.")
    void findPosts() {
        // Given
        List<Post> posts = Arrays.asList(
                new Post("제목1", "내용1"),
                new Post("제목2", "내용2")
        );
        when(postRepository.findAll()).thenReturn(posts);

        // When
        List<Post> foundPosts = postService.findPosts();

        // Then
        assertEquals(posts.size(), foundPosts.size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("게시글을 단건 조회할 수 있다.")
    void findPost() {
        // Given
        Long postId = 1L;
        Post post = new Post("제목", "내용");
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // When
        Post foundPost = postService.findPost(postId);

        // Then
        assertEquals(postId, foundPost.getId());
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회 시 NoSuchElementException을 던진다.")
    void findPost_NotFound() {
        // Given
        Long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(NoSuchElementException.class, () -> postService.findPost(postId));
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    @DisplayName("게시글을 삭제할 수 있다.")
    void deletePost() {
        // Given
        Long postId = 1L;

        // When
        postService.deletePost(postId);

        // Then
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    @DisplayName("게시글을 수정할 수 있다.")
    @Transactional
    public void updatePost(PostDTO postDTO, Long postId) {
        postRepository
                .findById(postId)
                .orElseThrow(() -> new NoSuchElementException("id" + postId + "에 해당하는 게시글이 없습니다."))
                .update(postDTO.title(), postDTO.description());
    }
}
