package com.NC13.studyBoard.repository;

import com.NC13.studyBoard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
