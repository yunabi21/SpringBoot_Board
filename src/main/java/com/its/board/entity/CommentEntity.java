package com.its.board.entity;

import com.its.board.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @Column
  private String commentWriter;

  @Column
  private String commentContents;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private MemberEntity memberEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;

  public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity, MemberEntity memberEntity) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setCommentWriter(memberEntity.getMemberEmail());
    commentEntity.setCommentContents(commentDTO.getCommentContents());
    commentEntity.setBoardEntity(boardEntity);
    commentEntity.setMemberEntity(memberEntity);

    return commentEntity;
  }
}
