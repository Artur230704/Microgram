package com.example.microgram.controllers;

import com.example.microgram.dtos.UserDto;
import com.example.microgram.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("users/registration")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.register(userDto), HttpStatus.OK);
    }

    @GetMapping("/users/name/{username}")
    public ResponseEntity<String> findByName(@PathVariable String username){
        return new ResponseEntity<>(userService.findUserByName(username), HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<String> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }
}
