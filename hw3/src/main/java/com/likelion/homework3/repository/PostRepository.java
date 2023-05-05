package com.likelion.homework3.repository;

import com.likelion.homework3.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> {


    Optional<Post> findById(Long id);

    List<Post> findAll();


}
