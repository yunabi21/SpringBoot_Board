package com.its.board.repository;


import com.its.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

  // jpql(java persistence query language)
  @Modifying
  @Query(value = "UPDATE Board_table b SET b.board_hits = b.board_hits + 1 WHERE b.id = :id", nativeQuery = true)
  void boardHits(@Param("id") Long id);
}
