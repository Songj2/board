package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.CommentDTO;
import com.NC13.studyBoard.entity.Board;
import com.NC13.studyBoard.entity.Comment;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{
    private final CommentRepository commentRepository;


    @Override
    public List<CommentDTO> getBoardAll(Long id) {
        List<Comment> comments= commentRepository.findAllByBoardId(id);
        List<CommentDTO> list= new ArrayList<>();

        for (Comment comment: comments){
            Users user= comment.getUsers();
            CommentDTO commentDTO= CommentDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .depth(comment.getDepth())
                    .name(user.getName())
                    .build();
            list.add(commentDTO);
        }
        return list;
    }

    public List<CommentDTO> getCommentsList(Board board){
        List<Comment> comments=commentRepository.findByBoard(board);
        List<CommentDTO> list= new ArrayList<>();

        for (Comment comment:comments){
            Users user= comment.getUsers();
            CommentDTO commentDTO= CommentDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .depth(comment.getDepth())
                    .name(user.getName())
                    .build();
            list.add(commentDTO);
        }
        return list;

    }
}
