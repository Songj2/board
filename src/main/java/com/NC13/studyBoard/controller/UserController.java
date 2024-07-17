package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.dto.InsertUserRequest;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UsersServiceImpl usersService;

    @GetMapping("login")
    public String showLogin() {

        return "user/login";
    }

    @GetMapping("register")
    public String showRegister() {
        return "user/register";
    }

    @PostMapping("register")
    public String runRegister(InsertUserRequest request) {
        log.info("UserController의 경로를 탔다!");
        Users users = usersService.selectUser(request.getEmail());
        log.debug("받음" + String.valueOf(users));

        if (users == null) {
            usersService.save(request);
            return "redirect:/";
        }

        return "redirect:/error";
    }

}
