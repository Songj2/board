package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.InsertUserRequest;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.enums.Role;
import com.NC13.studyBoard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    @Override
    public Users selectUser(String email) {
        log.debug("UserServiceImpl: 들어옴");
        Users users= usersRepository.findByEmailUser(email);
        if (users== null){
            log.debug("널을 반환");
            return null;
        }
        log.debug("엔티티를 반환");
        return users;
    }
//    @Override
//    public Users selectUser(String email) {
//        log.debug("UserServiceImpl: 들어옴");
//        Users users= usersRepository.findEmailJoinFetchBoard(email);
//        if (users==null){
//            log.debug("널을 반환");
//            return null;
//        }
//        log.debug("엔티티를 반환");
//        return users;
//    }
}
