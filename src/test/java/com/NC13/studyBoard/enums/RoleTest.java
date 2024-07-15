package com.NC13.studyBoard.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTest {
    private Role role;
    @Test
    @DisplayName("enum 가져오기")
    public void enumTest(){
        System.out.println("결과: "+Role.USER);
        System.out.println("결과: "+Role.valueOf("USER"));
    }
}
