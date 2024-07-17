package com.NC13.studyBoard.repository;

import com.NC13.studyBoard.entity.Board;
import com.NC13.studyBoard.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UsersRepository usersRepository;
    private Users users;

    @BeforeEach
    public void makeUser() {
        users = usersRepository.findById(2L).get();
    }

    @Test
    public void testClass() {
        System.out.println(boardRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        for (int i=0; i<300; i++){
            Board board = Board.builder().title("test").content("test content ").users(users).build();
            boardRepository.save(board);
        }

    }

    @Test
    public void testSelect() {
        Optional<Board> result = boardRepository.findById(1L);
        if (result.isPresent()) {
            Board board = result.get();
            System.out.println(board);
        }
    }

    @Test
    public void testUpdate() {
        Board board = Board.builder().id(2L).content("b").users(users).build();
        System.out.println(boardRepository.save(board));
    }

    @Test
    public void testDelete() {
        boardRepository.deleteById(2L);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 15);
        Page<Board> result = boardRepository.findAll(pageable);
        System.out.println(result);

        System.out.println("=======================");
        System.out.println("TotalPage: " + result.getTotalPages());
        System.out.println("TotalCount: " + result.getTotalElements());
        System.out.println("PageNumber:" + result.getNumber());
        System.out.println("PageSize: " + result.getSize());
        System.out.println("has next page? " + result.hasNext());
        System.out.println("first page? " + result.isFirst());
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 15, sort);
        Page<Board> res = boardRepository.findAll(pageable);
        res.get().forEach(System.out::println);
    }
}
