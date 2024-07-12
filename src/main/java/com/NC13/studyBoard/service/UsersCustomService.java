package com.NC13.studyBoard.service;

import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersCustomService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("usersDetailService");
        Optional<Users> users= usersRepository.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException(email);
        }
        Users usersEntity = users.get();
        System.out.println(usersEntity);
        return User.builder()
                .username(usersEntity.getEmail())
                .password(usersEntity.getPassword())
                .roles(usersEntity.getRole())
                .build();
    }
}
