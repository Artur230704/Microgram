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
public class LikeDto {
    private Long likeId;
    private Long userId;

    // an objectType is either a comment or a publication
    private String objectType;
    private Long publicationId;
    private Long commentId;
    private LocalDate likeDate;
}
