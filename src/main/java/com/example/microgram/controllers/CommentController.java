package com.example.microgram.controllers;

import com.example.microgram.dtos.comment.CommentAddingDTO;
import com.example.microgram.dtos.comment.CommentDisplayDTO;
import com.example.microgram.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/comments/adding")
    public void addComment(@ModelAttribute CommentAddingDTO commentAddingDTO, Authentication authentication){
        String email = authentication.getName();
        commentService.addComment(commentAddingDTO,email);
    }

    @GetMapping(value = "/comments/{publicationId}")
    public List<CommentDisplayDTO> getComment(@PathVariable Long publicationId){
        return commentService.getComments(publicationId);
    }

    @GetMapping(value = "/comments/deletion/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(commentService.deleteComment(commentId,email),HttpStatus.OK);
    }
}
