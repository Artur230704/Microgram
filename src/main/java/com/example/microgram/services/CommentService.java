package com.example.microgram.services;

import com.example.microgram.daos.CommentDao;
import com.example.microgram.dtos.comment.CommentAddingDTO;
import com.example.microgram.dtos.comment.CommentDisplayDTO;
import com.example.microgram.helper.DBHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final DBHelper DBHelper;

    public void addComment(CommentAddingDTO commentAddingDTO, String email){
        Long userId = DBHelper.getUserIdByEmail(email);
        commentDao.addComment(commentAddingDTO, userId);
    }

    public List<CommentDisplayDTO> getComments(Long publicationId){
        return commentDao.getComments(publicationId);
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
