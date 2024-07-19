package com.NC13.studyBoard.service;

import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersCustomService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users= usersRepository.findByEmail(email);
        if (users==null) {
            throw new UsernameNotFoundException("해당 사용자의 정보가 존재하지 않습니다.");
        }
        log.info("userCustomerService "+ users);
        log.info("userCustomerService "+ users.getRole());
        return User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRole().name())
                .build();
    }
}
