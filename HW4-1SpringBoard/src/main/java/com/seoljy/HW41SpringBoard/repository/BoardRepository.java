package com.seoljy.HW41SpringBoard.repository;

import com.seoljy.HW41SpringBoard.domain.Post;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Post save(Post member);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    void deleteById(Long id);
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByTitleContaining(String keyword);
}
