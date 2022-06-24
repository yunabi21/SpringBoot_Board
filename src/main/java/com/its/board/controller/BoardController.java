package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  public final BoardService boardService;

  @GetMapping("/save-form")
  public String saveForm() {
    System.out.println("BoardController.saveForm");

    return "/boardPages/saveForm";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute BoardDTO boardDTO) {
    System.out.println("BoardController.save");

    boardService.save(boardDTO);

    return "index";
  }

  @GetMapping("/")
  public String findAll(Model model) {
    System.out.println("BoardController.findAll");

    List<BoardDTO> boardDTOList = boardService.findAll();
    model.addAttribute("boardList", boardDTOList);

    return "/boardPages/list";
  }
}
