package com.example.microgram.services;

import com.example.microgram.daos.PublicationDao;
import com.example.microgram.dtos.PublicationDto;
import com.example.microgram.entities.Publication;
import com.example.microgram.helper.RowExistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationDao publicationDao;
    private final RowExistence rowExistence;

    public String addPublication(PublicationDto publicationDto, String userEmail){
        if (publicationDto.getImage() == null){
            if (publicationDto.getDescription() == null){
                return "You can not add empty publication";
            }
        }

        return publicationDao.addPublication(publicationDto, userEmail);
    }

    public List<PublicationDto> getUsersPublication(Long userId){
        List<Publication> publications = publicationDao.getUsersPublication(userId);
        return publications.stream()
                .map(PublicationDto::from)
                .collect(Collectors.toList());
    }

    public List<PublicationDto> getNewsline(String email){
        List<Publication> publications = publicationDao.getNewsline(email);
        return publications.stream()
                .map(PublicationDto::from)
                .collect(Collectors.toList());
    }

    public String delete(String email, Long id){
        if (!rowExistence.getPublicationExistenceById(id)){
            return "there is no a publication with id " + id;
        }

        publicationDao.delete(email,id);

        if (rowExistence.getPublicationExistenceById(id)){
            return "The publication can not be deleted";
        }

        return "Publication deleted";
    }
}
