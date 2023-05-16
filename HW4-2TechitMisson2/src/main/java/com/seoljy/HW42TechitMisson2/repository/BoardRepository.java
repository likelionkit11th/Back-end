package com.seoljy.HW42TechitMisson2.repository;

import com.seoljy.HW42TechitMisson2.model.BoardDto;

import java.util.Collection;

public interface BoardRepository {
    BoardDto create(BoardDto dto);
    BoardDto read(Long id);
    Collection<BoardDto> readAll();
    boolean update(Long id, BoardDto dto);
    boolean delete(Long id);
}
