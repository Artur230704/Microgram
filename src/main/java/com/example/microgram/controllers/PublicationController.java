package com.example.microgram.controllers;

import com.example.microgram.dtos.publication.PublicationAddingDTO;
import com.example.microgram.dtos.publication.PublicationDisplayDTO;
import com.example.microgram.services.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping("/publications")
    public String getPublicationPage(){
        return "publications";
    }

    @GetMapping("/publications/all")
    public ResponseEntity<List<PublicationDisplayDTO>> getAllPublications(){
        return new ResponseEntity<>(publicationService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/publications/adding")
    public ResponseEntity<PublicationDisplayDTO> addPublication(@ModelAttribute PublicationAddingDTO publication,
                                 Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(publicationService.addPublication(publication,email), HttpStatus.OK);
    }

    @GetMapping("/publications/user/{userId}")
    public ResponseEntity<List<PublicationDisplayDTO>> getUsersPublication(@PathVariable Long userId){
        return new ResponseEntity<>(publicationService.getUsersPublication(userId), HttpStatus.OK);
    }

    @GetMapping("/publications/newsline")
    public ResponseEntity<List<PublicationDisplayDTO>> getNewsline(Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(publicationService.getNewsline(email), HttpStatus.OK);
    }

    @GetMapping("/publications/deletion/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(publicationService.delete(email, id), HttpStatus.OK);
    }
}
