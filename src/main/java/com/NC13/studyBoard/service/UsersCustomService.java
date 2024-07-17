package com.NC13.studyBoard.service;

import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.enums.Role;
import com.NC13.studyBoard.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UsersCustomService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Users> users= usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        Users usersEntity = users.get(0);
        log.info("userCustomerService "+ usersEntity);
        log.info("userCustomerService "+ usersEntity.getRole());
        return User.builder()
                .username(usersEntity.getEmail())
                .password(usersEntity.getPassword())
//                .roles(String.valueOf(Role.USER))
                .roles(usersEntity.getRole().name())
                .build();
    }
}
