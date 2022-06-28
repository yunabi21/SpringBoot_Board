package com.its.board.dto;

import com.its.board.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
  private Long id;
  private Long boardId;
  private String commentWriter;
  private String commentContents;

  public CommentDTO(String commentWriter, String commentContents) {
    this.commentWriter = commentWriter;
    this.commentContents = commentContents;
  }

  public static CommentDTO toSaveDTO(CommentEntity commentEntity) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(commentEntity.getId());
    commentDTO.setCommentWriter(commentEntity.getCommentWriter());
    commentDTO.setCommentContents(commentEntity.getCommentContents());

    return commentDTO;
  }
}
