package com.example.microgram.controllers;

import com.example.microgram.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/subscriptions/subscribe/{userId}")
    public ResponseEntity<String> subscribe(@PathVariable Long userId, Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(subscriptionService.subscribe(email,userId), HttpStatus.OK);
    }
}
