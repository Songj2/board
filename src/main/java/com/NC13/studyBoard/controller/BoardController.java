package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.dto.BoardDTO;
import com.NC13.studyBoard.dto.UsersDTO;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.service.BoardService;
import com.NC13.studyBoard.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UsersServiceImpl usersService;

    @GetMapping("showOne/{id}")
    public String showOne(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        BoardDTO boardDTO = boardService.getOne(id);
        model.addAttribute("board", boardDTO);

        log.debug("로그인 정보: " + user);
        if (user != null) {
            log.debug("user가 null이 아니다!" + user.getUsername());
            Users users = usersService.selectUser(user.getUsername());
            UsersDTO usersDTO = UsersDTO.builder()
                    .email(users.getEmail())
                    .name(users.getName())
                    .password(users.getPassword())
                    .build();
            log.debug("DTO의 email값: " + usersDTO.getEmail());
            model.addAttribute("login", usersDTO);
        } else {
            model.addAttribute("login", null);
        }
        return "board/one";
    }
}
