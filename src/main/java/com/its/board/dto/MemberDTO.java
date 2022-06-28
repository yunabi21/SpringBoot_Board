package com.its.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
  private Long id;
  private String memberEmail;
  private String memberPassword;
  private String memberName;

  public MemberDTO(String memberEmail, String memberPassword, String memberName) {
    this.memberEmail = memberEmail;
    this.memberPassword = memberPassword;
    this.memberName = memberName;
  }
}
