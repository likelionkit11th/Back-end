package com.seol.HW3SpringBoard;

import com.seol.HW3SpringBoard.repository.BoardRepository;
import com.seol.HW3SpringBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringConfig {
    private final BoardRepository boardRepository;
    @Autowired
    public SpringConfig(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @Bean
    public BoardService boardService() {
        return new BoardService(boardRepository);
    }
}
