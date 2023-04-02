package com.example.microgram.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private Long likeId;
    private Long userId;
    private String objectType;
    private Long publicationId;
    private Long commentId;
    private LocalDate likeDate;
}
