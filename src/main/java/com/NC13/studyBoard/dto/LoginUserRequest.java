package com.NC13.studyBoard.dto;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
