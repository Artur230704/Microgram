package com.example.microgram.controllers;

import com.example.microgram.dtos.PublicationDto;
import com.example.microgram.services.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    @PostMapping("/publications/adding")
    public ResponseEntity<String> addPublication(@RequestBody PublicationDto publicationDto, Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(publicationService.addPublication(publicationDto,email), HttpStatus.OK);
    }

    @GetMapping("/userPublications/{userId}")
    public ResponseEntity<List<PublicationDto>> getUsersPublication(@PathVariable Long userId){
        return new ResponseEntity<>(publicationService.getUsersPublication(userId), HttpStatus.OK);
    }

    @GetMapping("/publications/newsline")
    public ResponseEntity<List<PublicationDto>> getNewsline(Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(publicationService.getNewsline(email), HttpStatus.OK);
    }

    @GetMapping("/publications/deletion/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(publicationService.delete(email, id), HttpStatus.OK);
    }
}
