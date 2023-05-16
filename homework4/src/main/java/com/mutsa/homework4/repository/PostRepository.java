package com.mutsa.homework4.repository;

import com.mutsa.homework4.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);
    List<Post> findAllByOrderByCreatedAtDesc(); // 최신순으로 조회

}
