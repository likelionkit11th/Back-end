package likelion.PostSiteVer1.Service;

import likelion.PostSiteVer1.DTO.PostDTO;
import likelion.PostSiteVer1.Domain.Post;
import likelion.PostSiteVer1.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class PostService {
    private  final PostRepository postRepository;

    //새 post 만들기
    @Transactional
    public Long newPost(PostDTO postDTO){
        Post post = postDTO.toEntity();
        postRepository.save(post);
        return post.getId();
    }

    // post 단건 조회
    public Post findbyIdPost(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException(postId + "번 글이 없습니다"));
    }

    // post title 검색
    public Page<Post> findbyTitlePost(String Title, Pageable pageable){
        Sort sort = Sort.by(Sort.Direction.DESC,"createddate");
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<Post> page = postRepository.findByTitleContaining(Title,pageable);
        if (page.isEmpty() == true) {
            throw new NoSuchElementException("제목 " + Title + "에 해당하는 게시글이 없습니다.");
        }
        return page;
    }


    //post 전체 조회 (정렬됨)
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllOrderBycreateddateDesc(pageable);
    }

    //post 삭제
    @Transactional
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    //post 수정
    @Transactional
    public void editPost(PostDTO postDTO ,Long postId) {
        postRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException(postId + "번 글이 없습니다"))
                .update(postDTO.title(),postDTO.description());
    }
}