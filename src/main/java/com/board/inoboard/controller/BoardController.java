package com.board.inoboard.controller;

import com.board.inoboard.dto.BoardRequestDto;
import com.board.inoboard.dto.BoardResponseDto;
import com.board.inoboard.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;


    public BoardController(BoardService boardService) {this.boardService = boardService;}


    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoard(){
        return boardService.getBoard();
    }



    @PutMapping("/board/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id,requestDto);
    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

}
