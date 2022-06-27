package com.its.board.service;

import com.its.board.common.PagingConst;
import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;


  public Long save(BoardDTO boardDTO) throws IOException {
    System.out.println("BoardService.save");

    MultipartFile boardFile = boardDTO.getBoardFile();
    String boardFileName = boardFile.getOriginalFilename();

    if (!boardFile.isEmpty()) {
      boardFileName = System.currentTimeMillis() + "_" + boardFileName;
      String savePath = "D:\\springboot_img\\" + boardFileName;
      boardFile.transferTo(new File(savePath));
      boardDTO.setBoardFileName((boardFileName));
    }

    BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDTO);

    return boardRepository.save(boardEntity).getId();
  }

  @Transactional
  public BoardDTO findById(Long id) {
    System.out.println("BoardService.findById");

    //  조회수 처리
    // native sql: update board_table set boardHits = boardHits + 1 where id = ?
    boardRepository.boardHits(id);

    Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
    if (boardEntityOptional.isPresent()) {
      BoardEntity boardEntity = boardEntityOptional.get();
      return BoardDTO.toBoardDTO(boardEntity);
    } else {
      return null;
    }
  }

  public List<BoardDTO> findAll() {
    System.out.println("BoardService.findAll");

    List<BoardEntity> boardEntityList = boardRepository.findAll();
    List<BoardDTO> boardDTOList = new ArrayList<>();

    for (BoardEntity boardEntity : boardEntityList) {
      boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
    }

    return boardDTOList;
  }

  @Transactional
  public void update(BoardDTO boardDTO) throws IOException {
    System.out.println("BoardService.update");

    BoardDTO findDTO = findById(boardDTO.getId());

    MultipartFile boardFile = boardDTO.getBoardFile();
    String boardFileName = boardFile.getOriginalFilename();

    if (!Objects.equals(findDTO.getBoardFileName(), boardDTO.getBoardFileName())) {

      if (!boardFile.isEmpty()) {
        System.out.println("다른 파일로 수정");

        boardFileName = System.currentTimeMillis() + "_" + boardFileName;
        String savePath = "D:\\springboot_img\\" + boardFileName;
        boardFile.transferTo(new File(savePath));
        boardDTO.setBoardFileName(boardFileName);
      } else {
        System.out.println("파일을 없앨 때");

        boardDTO.setBoardFileName(null);
      }

    } else if (findDTO.getBoardFileName() == null) {

      if (!boardFile.isEmpty()) {
        System.out.println("파일 넣기");

        boardFileName = System.currentTimeMillis() + "_" + boardFileName;
        String savePath = "D:\\springboot_img\\" + boardFileName;
        boardFile.transferTo(new File(savePath));
        boardDTO.setBoardFileName(boardFileName);
      } else {
        System.out.println("그대로 null");

        boardDTO.setBoardFileName(null);
      }
    }

    BoardEntity boardEntity = BoardEntity.toUpdateBoardEntity(boardDTO);
    System.out.println(boardEntity);

    boardRepository.save(boardEntity);
  }

  public void delete(Long id) {
    System.out.println("BoardService.delete");

    boardRepository.deleteById(id);
  }

  public Page<BoardDTO> paging(Pageable pageable) {
    int page = pageable.getPageNumber();
    // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1;
    page = (page == 1)? 0: (page-1);
    Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

    // Page<BoardEntity> => Page<BoardDTO>
    Page<BoardDTO> boardList = boardEntities.map(
            board -> new BoardDTO(board.getId(),
                    board.getBoardTitle(),
                    board.getBoardWriter(),
                    board.getBoardHits(),
                    board.getCreatedTime()
            ));

    return boardList;
  }
}
