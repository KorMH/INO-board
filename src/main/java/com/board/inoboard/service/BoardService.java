package com.board.inoboard.service;

import com.board.inoboard.dto.BoardRequestDto;
import com.board.inoboard.dto.BoardResponseDto;
import com.board.inoboard.dto.DeleteBoardDto;
import com.board.inoboard.entity.Board;
import com.board.inoboard.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){ this.boardRepository = boardRepository;}

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto){
        // ReuqestDto -> Entity
        Board board = new Board(requestDto);

        // DB 저장
        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoard() {
        //DB조회
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Board board = findboard(id);

        BoardResponseDto ResponseDto = new BoardResponseDto(board);
        // board 내용 수정
        if(requestDto.getPassword().equals(board.getPassword())){
            board.update(requestDto);
            return ResponseDto;
        } else{
            return ResponseDto;
        }
    }


    public DeleteBoardDto deleteBoard(Long id, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findboard(id);
        DeleteBoardDto deleteBoardDto = new DeleteBoardDto();
        // board삭제
        if(requestDto.getPassword().equals(board.getPassword())) {
            boardRepository.delete(board);
            deleteBoardDto.setMsg("success");
        }else{
            deleteBoardDto.setMsg("fail");
        }
        return deleteBoardDto;
    }

    private Board findboard(Long id){
        return  boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글은 존재하지 않습니다.")
        );
    }

}
