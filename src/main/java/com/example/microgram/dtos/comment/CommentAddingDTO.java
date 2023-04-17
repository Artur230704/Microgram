package com.example.microgram.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentAddingDTO {
    private Long userId;
    private Long publicationId;
    private String commentText;
}
