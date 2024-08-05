package com.NC13.studyBoard.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int depth;
    private Long ref;
    private Long step;
    private String name;
    private Long boardId;
}
