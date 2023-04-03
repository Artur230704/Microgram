package com.example.microgram.controllers;

import com.example.microgram.dtos.CommentDto;
import com.example.microgram.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/comments/adding")
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto, Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(commentService.addComment(commentDto,email), HttpStatus.OK);
    }

    @GetMapping(value = "/comments/deletion/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(commentService.deleteComment(commentId,email),HttpStatus.OK);
    }
}
