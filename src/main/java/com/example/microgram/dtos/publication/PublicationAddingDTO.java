package com.example.microgram.dtos.publication;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicationAddingDTO {
    private Long userId;
    private MultipartFile image;
    private byte[] imageBytes;
    private String description;

    @SneakyThrows
    public PublicationAddingDTO(MultipartFile image, String description) {
        this.image = image;
        this.imageBytes = image.getBytes();
        this.description = description;
    }
}
