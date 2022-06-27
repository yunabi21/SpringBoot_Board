package com.its.board.dto;

import com.its.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
  private Long id;
  private String boardTitle;
  private String boardWriter;
  private String boardPassword;
  private String boardContents;
  private int boardHits;
  private LocalDateTime boardCreatedTime;
  private LocalDateTime boardUpdatedTime;
  private MultipartFile boardFile;
  private String boardFileName;

  public BoardDTO(String boardTitle, String boardWriter, String boardPassword, String boardContents, int boardHits) {
    this.boardTitle = boardTitle;
    this.boardWriter = boardWriter;
    this.boardPassword = boardPassword;
    this.boardContents = boardContents;
    this.boardHits = boardHits;
  }

  public BoardDTO(Long id, String boardTitle, String boardWriter, int boardHits, LocalDateTime boardCreatedTime) {
    this.id = id;
    this.boardTitle = boardTitle;
    this.boardWriter = boardWriter;
    this.boardHits = boardHits;
    this.boardCreatedTime = boardCreatedTime;
  }

  public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
    System.out.println("BoardDTO.toBoardDTO");

    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setId(boardEntity.getId());
    boardDTO.setBoardTitle(boardEntity.getBoardTitle());
    boardDTO.setBoardWriter(boardEntity.getBoardWriter());
    boardDTO.setBoardPassword(boardEntity.getBoardPassword());
    boardDTO.setBoardContents(boardEntity.getBoardContents());
    boardDTO.setBoardHits(boardEntity.getBoardHits());
    boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
    boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
    boardDTO.setBoardFileName(boardEntity.getBoardFileName());

    return boardDTO;
  }
}
