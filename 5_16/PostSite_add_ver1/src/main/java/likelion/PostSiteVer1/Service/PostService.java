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

    @Transactional
    public Long newPost(PostDTO postDTO){
        Post post = postDTO.toEntity();
        postRepository.save(post);
        return post.getId();
    }


    public Post findbyIdPost(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException(postId + "번 글이 없습니다"));
    }
//    public List<Post> findbySortList(List<Post> post){
//        return post.stream()
//                .sorted(Comparator.comparing(Post::getCreatedAt))
//                .collect(Collectors.toList());
//    }

    public Page<Post> findbyTitlePost(String Title, Pageable pageable){
        Page<Post> page = postRepository.findByTitleContaining(Title,pageable);
        if (page.isEmpty() == true) {
            throw new NoSuchElementException("제목 " + Title + "에 해당하는 게시글이 없습니다.");
        }
        return page;
    }

    public Page<Post> findAllPosts(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);
        return postRepository.findAllOrderBycreatedAtDesc(pageable);
    }

/*
    public List<Post> findbyTitlePost(String Title) {
        List<Post> list = postRepository.findByTitleContaining(Title);
        if (list.isEmpty() == true) {
            throw new NoSuchElementException("검색 결과가 없습니다");
        }
        return list;
    }
    public List<Post> findAllPosts() {
            List<Post> list= postRepository.findAllOrderBycreatedAtDesc();
            return list;
    }
*/
    @Transactional
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    @Transactional
    public void editPost(PostDTO postDTO ,Long postId) {
        postRepository
                .findById(postId)
                .orElseThrow(()-> new NoSuchElementException(postId + "번 글이 없습니다"))
                .update(postDTO.title(),postDTO.description());
    }
}