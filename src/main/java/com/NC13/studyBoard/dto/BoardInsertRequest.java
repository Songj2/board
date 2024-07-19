package com.NC13.studyBoard.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardInsertRequest {

    private String title;
    private String content;
}
