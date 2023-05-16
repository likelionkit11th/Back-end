package com.minturtle.community.repository;

import com.minturtle.community.model.BoardDto;
import com.minturtle.community.model.PostDto;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class InMemoryPostRepository implements PostRepository{

    private static Long sequence = 0L;
    private final Map<Long, PostDto> memory = new HashMap<>();

    private final BoardRepository boardRepository;

    public InMemoryPostRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public PostDto create(Long boardId, PostDto dto) {
        final BoardDto boardDto = boardRepository.read(boardId);
        if(boardDto == null) return null;


        dto.setId(++sequence);
        dto.setBoardId(boardId);
        memory.put(sequence, dto);

        return memory.get(sequence);
    }

    @Override
    public PostDto read(Long boardId, Long postId) {
        final PostDto postDto = memory.getOrDefault(boardId, null);

        if(postDto == null) return null;

        else if(!Objects.equals(postDto.getBoardId(), boardId)){
            return null;
        }

        return postDto;
    }

    @Override
    public Collection<PostDto> readAll(Long boardId) {
        if(boardRepository.read(boardId) == null) return null;

        Collection<PostDto> postList = new ArrayList<>();


        memory.forEach((postId, postDto)->{
            if(Objects.equals(postDto.getBoardId(), boardId)){
                postList.add(postDto);
            }
        });

        return postList;
    }

    @Override
    public boolean update(Long boardId, Long postId , PostDto dto) {
        final PostDto targetPost = memory.getOrDefault(postId, null);
        if(targetPost == null) return false;
        else if(!Objects.equals(targetPost.getBoardId(), boardId)){
            return false;
        }
        else if(!targetPost.getPassword().equals(dto.getPassword())){ return false; }


        targetPost.setTitle(dto.getTitle() == null ? targetPost.getTitle() : dto.getTitle());
        targetPost.setContent(dto.getContent() == null ? targetPost.getContent() : dto.getContent());


        return true;
    }

    @Override
    public boolean delete(Long boardId, Long postId, String password) {
        final PostDto targetPost = memory.getOrDefault(postId, null);
        if(targetPost == null) return false;
        else if(!Objects.equals(targetPost.getBoardId(), boardId)){
            return false;
        }
        else if(!targetPost.getPassword().equals(password)){ return false; }

        memory.remove(postId);
        return true;
    }
    
    
}
