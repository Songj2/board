package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.dto.BoardDTO;
import com.NC13.studyBoard.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final BoardServiceImpl boardService;
    @GetMapping("/")
    public String showMain(Model model, @PageableDefault(page = 0, sort = "id") Pageable pageable){
        Page<BoardDTO> posts= boardService.getAll(pageable);
        model.addAttribute("list", posts);

        return "board/list";
    }


}
