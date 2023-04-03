package com.example.microgram.controllers;

import com.example.microgram.dtos.LikeDto;
import com.example.microgram.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likes/like")
    public ResponseEntity<String> like(@RequestBody LikeDto likeDto, Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(likeService.like(likeDto,email), HttpStatus.OK);
    }
}
