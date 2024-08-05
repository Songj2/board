package com.NC13.studyBoard.repository;

import com.NC13.studyBoard.entity.Comment;
import com.NC13.studyBoard.repository.queryDSL.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    List<Comment> findAllByBoardId(Long id);
}

