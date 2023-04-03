package com.example.microgram.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private Long userId;
    private Long publicationId;
    private String commentText;
    private LocalDate commentDate;
}
