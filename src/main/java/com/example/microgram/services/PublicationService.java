package com.example.microgram.services;

import com.example.microgram.daos.PublicationDao;
import com.example.microgram.dtos.publication.PublicationAddingDTO;
import com.example.microgram.dtos.publication.PublicationDisplayDTO;
import com.example.microgram.helper.DBHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationDao publicationDao;
    private final DBHelper DBHelper;

    public List<PublicationDisplayDTO> getAll(){
        return publicationDao.getAll()
                .stream()
                .peek(PublicationDisplayDTO::parseByteToString)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public PublicationDisplayDTO addPublication(PublicationAddingDTO publicationDto, String email){
        Long userId = DBHelper.getUserIdByEmail(email);
        publicationDto.setImageBytes(publicationDto.getImage().getBytes());
        publicationDao.addPublication(publicationDto, userId);
        PublicationDisplayDTO publication = publicationDao.getAddedPublication();
        PublicationDisplayDTO.parseByteToString(publication);
        return publication;
    }

    public List<PublicationDisplayDTO> getUsersPublication(Long userId){
        return publicationDao.getUsersPublication(userId)
                .stream()
                .peek(PublicationDisplayDTO::parseByteToString)
                .collect(Collectors.toList());
    }

    public List<PublicationDisplayDTO> getNewsline(String email){
        Long userId = DBHelper.getUserIdByEmail(email);
        return publicationDao.getNewsline(userId)
                .stream()
                .peek(PublicationDisplayDTO::parseByteToString)
                .collect(Collectors.toList());
    }

    public String delete(String email, Long publicationId){
        if (!DBHelper.getPublicationExistenceById(publicationId)){
            return "there is no a publication with id " + publicationId;
        }

        Long userId = DBHelper.getUserIdByEmail(email);
        publicationDao.delete(userId,publicationId);

        if (DBHelper.getPublicationExistenceById(publicationId)){
            return "The publication can not be deleted";
        }

        return "Publication deleted";
    }
}
