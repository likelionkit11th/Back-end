package com.seoljy.HW41SpringBoard.repository;

import com.seoljy.HW41SpringBoard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Post save(Post member);
    Optional<Post> findById(Long id);
    Page<Post> findAll(Pageable pageable);
    void deleteById(Long id);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
