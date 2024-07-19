package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.dto.InsertUserRequest;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.service.UsersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UsersServiceImpl usersService;

    @GetMapping("login")
    public String showLogin(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception) {
        log.debug(error+"에러와 익셉션"+ exception);
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
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
            return "redirect:/user/login";
        }
        throw new DataIntegrityViolationException("'" + request.getEmail() + "'는 이미 가입된 이메일입니다.");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/user/login";
    }

}
