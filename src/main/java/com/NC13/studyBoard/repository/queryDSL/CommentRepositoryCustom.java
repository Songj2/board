package com.NC13.studyBoard.repository.queryDSL;

import com.NC13.studyBoard.entity.Board;
import com.NC13.studyBoard.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findByBoard(Board board);
    List<Comment> findByComment(Comment comment);
    Long getRefMax(Board board);
}
