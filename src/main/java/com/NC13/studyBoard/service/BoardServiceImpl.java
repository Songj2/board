package com.NC13.studyBoard.service;

import com.NC13.studyBoard.dto.BoardDTO;
import com.NC13.studyBoard.entity.Board;
import com.NC13.studyBoard.entity.Users;
import com.NC13.studyBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public List<BoardDTO> getAll() {
        List<Board> posts = boardRepository.findAll();
        List<BoardDTO> list = new ArrayList<>();

        for (Board post : posts) {
            Users users = post.getUsers();

            BoardDTO boardDTO = BoardDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .updateAt(post.getUpdateAt())
                    .userId(users.getId())
                    .name(users.getName())
            .build();

            list.add(boardDTO);
        }

        return list;
    }

    @Override
    public Page<BoardDTO> getAll(Pageable pageable) {
        Page<Board> boards= boardRepository.findAll(pageable);
        List<BoardDTO> boardDTOS= new ArrayList<>();

        for (Board post : boards) {
            Users users = post.getUsers();

            BoardDTO boardDTO = BoardDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .updateAt(post.getUpdateAt())
                    .userId(users.getId())
                    .name(users.getName())
                    .build();

            boardDTOS.add(boardDTO);
        }
        return new PageImpl<>(boardDTOS, pageable, boards.getTotalElements());
    }

    @Override
    public BoardDTO getOne(Long id) {
        Optional<Board> board= boardRepository.findById(id);
        Board boardEntity= board.orElse(null);

        Users users= boardEntity.getUsers();

        BoardDTO boardDTO= BoardDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .updateAt(boardEntity.getUpdateAt())
                .userId(users.getId())
                .name(users.getName())
                .build();
        return boardDTO;
    }
}
