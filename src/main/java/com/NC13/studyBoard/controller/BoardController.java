package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.dto.BoardDTO;
import com.NC13.studyBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("showOne/{id}")
    public String showOne(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO= boardService.getOne(id);
        model.addAttribute("board", boardDTO);
        return "board/one";
    }
}
