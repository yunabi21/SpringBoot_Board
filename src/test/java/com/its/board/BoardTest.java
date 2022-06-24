package com.its.board;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {
  @Autowired
  private BoardService boardService;

  @Test
  @Transactional
  @Rollback
  public void saveTest() {
    System.out.println("boardTest.saveTest");

    BoardDTO boardDTO = new BoardDTO("qqq", "qwe", "qwe", "bbb", 0);
    Long saveId = boardService.save(boardDTO);

    BoardDTO saveDTO = boardService.findById(saveId);

    assertThat(boardDTO.getBoardContents()).isEqualTo(saveDTO.getBoardContents());
  }
}
