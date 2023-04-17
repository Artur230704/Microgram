package com.example.microgram.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    private Long publicationId;
    private Long userId;
    private byte[] image;
    private String description;
    private LocalDate publicationDate;
}
