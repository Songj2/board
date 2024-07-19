package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.UserInsertRequest;
import com.NC13.studyBoard.entity.Users;

public interface UsersService {
    Long save(UserInsertRequest insertUserRequest);

    Users selectUser(String email);
}
