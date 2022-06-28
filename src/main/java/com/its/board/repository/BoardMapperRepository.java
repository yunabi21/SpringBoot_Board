package com.its.board.repository;

import com.its.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapperRepository {
  List<BoardDTO> boardList();

  void save(BoardDTO boardDTO);
}
