package com.NC13.studyBoard.rest;

import com.NC13.studyBoard.dto.UserInsertRequest;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UsersServiceImpl usersService;
    @PostMapping("/register")
    public Map<String, Object> runRegister(@RequestParam String email, @RequestParam String password, @RequestParam String name) {
        log.info("RestController의 경로를 탔다!"+email+'\t'+password+'\t'+name);
        Map<String, Object> resultMap= new HashMap<>();
        resultMap.put("result", "fail");
        UserInsertRequest request= new UserInsertRequest();

        Users users = usersService.selectUser(email);
        log.debug("받음" + String.valueOf(users));

        if (users == null) {
            request.setEmail(email);
            request.setName(name);
            request.setPassword(password);
            usersService.save(request);
            resultMap.put("result", "success");
        }
        return resultMap;
    }
}
