package com.its.board.entity;

import com.its.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @ColumnDefault("0")
  private int boardHits;

  public static BoardEntity toBoardEntity(BoardDTO boardDTO) {
    System.out.println("BoardEntity.toBoardEntity");

    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setBoardTitle(boardDTO.getBoardTitle());
    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
    boardEntity.setBoardPassword(boardDTO.getBoardPassword());
    boardEntity.setBoardContents(boardDTO.getBoardContents());
    boardEntity.setBoardHits(boardDTO.getBoardHits());

    return boardEntity;
  }

  public static BoardEntity toUpdateBoardEntity(BoardDTO boardDTO) {
    System.out.println("BoardEntity.toUpdateBoardEntity");

    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setId(boardDTO.getId());
    boardEntity.setBoardTitle(boardDTO.getBoardTitle());
    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
    boardEntity.setBoardPassword(boardDTO.getBoardPassword());
    boardEntity.setBoardContents(boardDTO.getBoardContents());
    boardEntity.setBoardHits(boardDTO.getBoardHits());

    return boardEntity;
  }
}
