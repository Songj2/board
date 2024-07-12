package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getAll();
    Page<BoardDTO> getAll(Pageable pageable);
    BoardDTO getOne(Long id);
}
