package likelion.techit_second.Repository;

import likelion.techit_second.model.BoardDTO;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Repository
public class InMemoryBoardRepository implements BoardRepository{
    private Long lastIndex = 0L;
    private final Map<Long, BoardDTO> memory = new HashMap<>();
    @Override
    public BoardDTO create(BoardDTO dto) {
        lastIndex++;
        dto.setId(lastIndex);
        memory.put(lastIndex,dto);
        return memory.get(lastIndex);
    }

    @Override
    public BoardDTO read(Long id) {
        return memory.getOrDefault(id,null);
    }

    @Override
    public Collection<BoardDTO> readAll() {
        return memory.values();
    }

    @Override
    public boolean update(Long id, BoardDTO dto) {
        if (memory.containsKey(id)){
            dto.setId(id);
            memory.put(id,dto);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {

        if (memory.containsKey(id)){
            memory.remove(id);
            return true;
        }return false;
    }
}
