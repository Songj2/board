package com.NC13.studyBoard.dto;

import lombok.Data;

@Data
public class InsertUserRequest {
    private String email;
    private String password;
    private String name;
}
