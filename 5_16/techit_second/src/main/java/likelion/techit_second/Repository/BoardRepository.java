package likelion.techit_second.Repository;

import likelion.techit_second.model.BoardDTO;

import java.util.Collection;

public interface BoardRepository {
    BoardDTO create(BoardDTO dto);
    BoardDTO read(Long id);
    Collection<BoardDTO> readAll();
    boolean update(Long id, BoardDTO dto);
    boolean delete(Long id);

}
