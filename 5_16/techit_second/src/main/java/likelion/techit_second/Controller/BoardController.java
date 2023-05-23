package likelion.techit_second.Controller;

import likelion.techit_second.Repository.BoardRepository;
import likelion.techit_second.model.BoardDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @PutMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO dto){
        return ResponseEntity.ok(boardRepository.create(dto));
    }
    @GetMapping("{id}")
    public ResponseEntity<BoardDTO> readBoard(@PathVariable("id") Long id){
        BoardDTO dto = boardRepository.read(id);
        if (dto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<Collection<BoardDTO>> readBoardAll() {
        return ResponseEntity.ok(this.boardRepository.readAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBoard(@PathVariable("id") Long id, @RequestBody BoardDTO dto) {
        if (!boardRepository.update(id, dto)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        if (!boardRepository.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
