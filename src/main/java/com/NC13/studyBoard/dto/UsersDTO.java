package com.NC13.studyBoard.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class UsersDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
