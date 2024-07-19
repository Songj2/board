package com.NC13.studyBoard.repository;

import com.NC13.studyBoard.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

//    @Query("select u from Users u join fetch u.boards where u.email=?1")
//    Users findEmailJoinFetchBoard(String email);
    @Query("select u from Users u where u.email=?1")
    Users findByEmailUser(String email);
}
