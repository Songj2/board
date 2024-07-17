package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.InsertUserRequest;
import com.NC13.studyBoard.entity.Users;

public interface UsersService {
    Long save(InsertUserRequest insertUserRequest);

    Users selectUser(String email);
}
