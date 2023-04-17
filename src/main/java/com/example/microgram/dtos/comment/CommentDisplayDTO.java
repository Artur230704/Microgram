package com.example.microgram.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDisplayDTO {
    private Long publication_id;
    private String email; // user email
    private String commentText;
}
