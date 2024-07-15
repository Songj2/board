package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.InsertUserRequest;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.enums.Role;
import com.NC13.studyBoard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long save(InsertUserRequest insertUserRequest) {
        return usersRepository.save(Users.builder()
                .email(insertUserRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(insertUserRequest.getPassword()))
                .name(insertUserRequest.getName())
                        .role(Role.USER)
                .build()).getId();
    }
}
