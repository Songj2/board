package com.NC13.studyBoard.repository.queryDSL.impl;

import com.NC13.studyBoard.entity.Board;
import com.NC13.studyBoard.entity.Comment;
import com.NC13.studyBoard.entity.QComment;
import com.NC13.studyBoard.repository.queryDSL.CommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Comment> findByBoard(Board board) {
        QComment comment= QComment.comment;
        return jpaQueryFactory.selectFrom(comment)
                .where(comment.board.eq(board))
                .orderBy(comment.ref.asc(),
                        comment.step.asc(),
                        comment.depth.asc())
                .fetch();

    }

    @Override
    public List<Comment> findByComment(Comment comment) {
        QComment qComment= QComment.comment;

        return jpaQueryFactory.selectFrom(qComment)
                .where(qComment.ref.eq(comment.getRef()),
                        qComment.step.gt(comment.getStep()))
                .orderBy(qComment.step.asc(),
                        qComment.depth.asc())
                .fetch();
    }

    @Override
    public Long getRefMax(Board board) {
        QComment comment= QComment.comment;
        return jpaQueryFactory.select(comment.ref.max())
                .from(comment)
                .where(comment.board.eq(board))
                .fetchFirst();
    }
}
