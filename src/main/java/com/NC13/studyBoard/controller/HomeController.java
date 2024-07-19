package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.dto.BoardDTO;
import com.NC13.studyBoard.dto.UsersDTO;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.service.BoardServiceImpl;
import com.NC13.studyBoard.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final BoardServiceImpl boardService;
    private final UsersServiceImpl usersService;

    @GetMapping("/")
    public String showMain(Model model, @PageableDefault(page = 0, sort = "id") Pageable pageable, @AuthenticationPrincipal User user) {
        Page<BoardDTO> posts = boardService.getAll(pageable);
        model.addAttribute("list", posts);
        log.debug("로그인 정보: " + user);
        if (user != null) {
            log.debug("user 가 null 이 아니다!" + user.getUsername());
            Users users = usersService.selectUser(user.getUsername());
            UsersDTO usersDTO = UsersDTO.builder()
                    .email(users.getEmail())
                    .name(users.getName())
                    .password(users.getPassword())
                    .build();
            log.debug("DTO의 email 값: " + usersDTO.getEmail());
            model.addAttribute("login", usersDTO);
        } else {
            model.addAttribute("login", null);
        }
        return "board/list";
    }

}
