package com.minturtle.community.controller;


import com.minturtle.community.model.BoardDto;
import com.minturtle.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardRepository boardRepository;



    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto dto){
        return ResponseEntity.ok(boardRepository.create(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> readBoard(@PathVariable Long id){
        BoardDto findBoard = boardRepository.read(id);

        if(findBoard == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(findBoard);
    }

    @GetMapping
    public ResponseEntity<Collection<BoardDto>> readBoardAll(){
        return ResponseEntity.ok(boardRepository.readAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBoard(@PathVariable("id") Long id, @RequestBody BoardDto dto){
        if(!boardRepository.update(id, dto)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id){
        if(!boardRepository.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
