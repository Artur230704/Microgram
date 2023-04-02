package com.example.microgram.dtos;

import com.example.microgram.entities.Publication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    private Long publicationId;
    private Long userId;
    private String image;
    private String description;
    private LocalDate publicationDate;


    public static PublicationDto from(Publication publication){
        return builder()
                .publicationId(publication.getPublicationId())
                .userId(publication.getUserId())
                .image(publication.getImage())
                .description(publication.getDescription())
                .publicationDate(publication.getPublicationDate())
                .build();
    }
}
