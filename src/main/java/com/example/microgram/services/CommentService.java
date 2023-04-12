package com.example.microgram.services;

import com.example.microgram.daos.CommentDao;
import com.example.microgram.dtos.CommentDto;
import com.example.microgram.helper.DBHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final DBHelper DBHelper;

    public String addComment(CommentDto commentDto, String email){
        if (commentDto.getPublicationId() == null){
            return "Publication id can not be empty";
        }
        if (!DBHelper.getPublicationExistenceById(commentDto.getPublicationId())){
            return "There is no publication with id " + commentDto.getPublicationId();
        }
        if (commentDto.getCommentText() == null){
            return "Comment text can not be empty";
        }

        Long userId = DBHelper.getUserIdByEmail(email);
        return commentDao.addComment(commentDto, userId);
    }

    public String deleteComment(Long commentId, String email){
        if (!DBHelper.getCommentExistenceById(commentId)){
            return "There is no comment with id " + commentId;
        }

        commentDao.deleteComment(commentId,email);

        if (DBHelper.getCommentExistenceById(commentId)){
            return "The comment can not be deleted";
        }

        return "Comment deleted";
    }
}
