package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getBoardAll(Long id);

}
