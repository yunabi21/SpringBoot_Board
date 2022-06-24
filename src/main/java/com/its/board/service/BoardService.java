package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;


  public Long save(BoardDTO boardDTO) {
    System.out.println("BoardService.save");

    BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDTO);

    return boardRepository.save(boardEntity).getId();
  }

  public BoardDTO findById(Long id) {
    System.out.println("BoardService.findById");

    Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
    if (boardEntityOptional.isPresent()) {
      BoardEntity boardEntity = boardEntityOptional.get();
      return BoardDTO.toBoardDTO(boardEntity);
    } else {
      return null;
    }
  }
}
