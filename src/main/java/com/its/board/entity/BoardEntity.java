package com.its.board.entity;

import com.its.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

  @Column
  private String boardFileName;

  //  회원(N)-게시글(1) 연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private MemberEntity memberEntity;

  // 게시글(1)-회원(N) 연관관계
  @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<CommentEntity> commentEntityList = new ArrayList<>();


  //  MemberEntity 와 연관관계 맺기 전
//  public static BoardEntity toBoardEntity(BoardDTO boardDTO) {
//    System.out.println("BoardEntity.toBoardEntity");
//
//    BoardEntity boardEntity = new BoardEntity();
//    boardEntity.setBoardTitle(boardDTO.getBoardTitle());
//    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
//    boardEntity.setBoardPassword(boardDTO.getBoardPassword());
//    boardEntity.setBoardContents(boardDTO.getBoardContents());
//    boardEntity.setBoardHits(boardDTO.getBoardHits());
//    boardEntity.setBoardFileName(boardDTO.getBoardFileName());
//
//    return boardEntity;
//  }

  //  회원과 연관관계 맺은 후
  public static BoardEntity toBoardEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
    System.out.println("BoardEntity.toBoardEntity");

    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setBoardTitle(boardDTO.getBoardTitle());

//    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
    boardEntity.setBoardWriter(memberEntity.getMemberEmail());  // 회원 이메일을 작성자로 한다면

    boardEntity.setBoardPassword(boardDTO.getBoardPassword());
    boardEntity.setBoardContents(boardDTO.getBoardContents());
    boardEntity.setBoardHits(boardDTO.getBoardHits());
    boardEntity.setBoardFileName(boardDTO.getBoardFileName());
    boardEntity.setMemberEntity(memberEntity);

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
    boardEntity.setBoardFileName(boardDTO.getBoardFileName());

    return boardEntity;
  }
}
