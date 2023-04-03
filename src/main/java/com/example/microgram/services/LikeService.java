package com.example.microgram.services;

import com.example.microgram.daos.LikeDao;
import com.example.microgram.dtos.LikeDto;
import com.example.microgram.helper.RowExistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeDao likeDao;
    private final RowExistence rowExistence;

    // an object is either a comment or a publication
    public String like(LikeDto likeDto, String userEmail){

        if (likeDto.getObjectType() == null){
            return "Object type can not be empty";
        }
        if (likeDto.getPublicationId() == null && likeDto.getCommentId() == null){
            return "Object id can not be empty";
        }


        if (likeDto.getObjectType().equalsIgnoreCase("Publication")){
            if (!rowExistence.getPublicationExistenceById(likeDto.getPublicationId())){
                return "There is no publication with id " + likeDto.getPublicationId();
            }
            return likeDao.likePublication(likeDto,userEmail);
        }

        if (likeDto.getObjectType().equalsIgnoreCase("Comment")){
            if (!rowExistence.getCommentExistenceById(likeDto.getCommentId())){
                return "There is no comment with id " + likeDto.getCommentId();
            }
            return likeDao.likeComment(likeDto,userEmail);
        }

        return "The like object is not defined";
    }
}
