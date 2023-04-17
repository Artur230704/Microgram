package com.example.microgram.dtos.publication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDisplayDTO {
    private Long publication_id;
    private String email;
    private byte[] image;
    private String imageString;
    private String description;

    public static void parseByteToString(PublicationDisplayDTO publication){
        publication.setImageString(Base64.getEncoder().encodeToString(publication.image));
    }
}
