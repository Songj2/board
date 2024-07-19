package com.NC13.studyBoard.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @GetMapping("/error")
    @ExceptionHandler(Throwable.class)
    public String exception(Model model, @RequestParam() HttpServletRequest request) {
        log.debug("에러 컨트롤러 진입");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String statusMessage = status.toString();

        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(statusMessage));
        model.addAttribute("status", httpStatus.getReasonPhrase());
        model.addAttribute("message", statusMessage);

        return "/error";
    }


}
