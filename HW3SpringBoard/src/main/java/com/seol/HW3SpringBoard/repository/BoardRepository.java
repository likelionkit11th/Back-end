package com.seol.HW3SpringBoard.repository;

import com.seol.HW3SpringBoard.domain.Post;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Post save(Post member);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    void deleteById(Long id);
}
