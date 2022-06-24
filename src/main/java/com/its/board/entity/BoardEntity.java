package com.its.board.entity;

import com.its.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "board_table")
public class BoardEntity {
  @Id
  @Column
  private Long id;

  @Column(length = 50)
  private String boardTitle;

  @Column(length = 20)
  private String boardWriter;

  @Column(length = 20)
  private String boardPassword;

  @Column(length = 500)
  private String boardContents;

  @Column
  private int boardHits;

  public BoardEntity toBoardEntity(BoardDTO boardDTO) {
    System.out.println("BoardEntity.toBoardEntity");

    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setBoardTitle(boardDTO.getBoardTitle());
    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
    boardEntity.setBoardPassword(boardDTO.getBoardPassword());
    boardEntity.setBoardContents(boardDTO.getBoardContents());
    boardEntity.setBoardHits(boardDTO.getBoardHits());

    return boardEntity;
  }
}
