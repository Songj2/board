package com.NC13.studyBoard.controller;

import com.NC13.studyBoard.entity.Board;
import com.NC13.studyBoard.entity.Comment;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.repository.BoardRepository;
import com.NC13.studyBoard.repository.CommentRepository;
import com.NC13.studyBoard.service.UsersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/comment/")
public class CommentController {
    private final CommentRepository commentRepository;
    private final UsersServiceImpl usersService;
    private final BoardRepository boardRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, UsersServiceImpl usersService, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.usersService = usersService;
    }

    @PostMapping("insert")
    public String insert(@RequestParam(name = "content") String content, @RequestParam(name = "boardId") Long boardId, @RequestParam(required = false, name = "parentId") Long parentId, @AuthenticationPrincipal User user) {
        log.debug("insert 동작중" + content + "\t" + parentId);
        Users users = usersService.selectUser(user.getUsername());
        Board board = boardRepository.findById(boardId).get();

        log.debug("보드"+board);
        if (parentId != null) { //coComment
            Comment parent = commentRepository.findById(parentId).get();
            List<Comment> list = commentRepository.findByComment(parent);


            Comment comm = Comment.builder()
                    .content(content)
                    .users(users)
                    .ref(parent.getRef())
                    .depth(parent.getDepth() + 1)
                    .step(parent.getStep() + 1)
                    .board(board)
                    .build();

            commentRepository.save(comm);

            for (Comment temp : list) {
                temp = Comment.builder()
                        .id(temp.getId())
                        .content(temp.getContent())
                        .users(users)
                        .ref(temp.getRef())
                        .depth(temp.getDepth())
                        .step(temp.getStep() + 1)
                        .createdAt(temp.getCreatedAt())
                        .board(board)
                        .build();
                commentRepository.save(temp);
            }
        } else { // Header Comment
            Comment comm = Comment.builder()
                    .content(content)
                    .users(users)
                    .ref(commentRepository.getRefMax(board)+1)
                    .depth(0)
                    .step(0L)
                    .board(board)
                    .build();
            commentRepository.save(comm);
        }
        return "redirect:/board/showOne/" + boardId;
    }
}
