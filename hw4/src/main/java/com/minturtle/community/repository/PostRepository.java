package com.minturtle.community.repository;

import com.minturtle.community.model.PostDto;

import java.util.Collection;

public interface PostRepository {

    PostDto create(Long boardId, PostDto dto);
    PostDto read(Long BoardId, Long postId);
    Collection<PostDto> readAll(Long boardId);
    boolean update(Long BoardId, Long postId, PostDto dto);
    boolean delete(Long BoardId, Long postId, String password);
}
