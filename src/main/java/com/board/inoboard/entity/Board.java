package com.board.inoboard.entity;

import com.board.inoboard.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String contents;
    private String title;
    private String password;


    public Board(BoardRequestDto boardrequestDto){
        this.title = boardrequestDto.getTitle();
        this.contents = boardrequestDto.getContents();
        this.password = boardrequestDto.getPassword();
        this.username = boardrequestDto.getUsername();
    }

    public void update(BoardRequestDto boardRequestDto){
        this.username = boardRequestDto.getUsername();
        this.contents = boardRequestDto.getContents();
        this.title = boardRequestDto.getTitle();
    }
}

