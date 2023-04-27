package com.example.microgram.controllers;

import com.example.microgram.dtos.user.UserDisplayDTO;
import com.example.microgram.dtos.user.UserLoginDTO;
import com.example.microgram.dtos.user.UserRegisterDTO;
import com.example.microgram.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@ComponentScan
public class UserController {
    private final UserService userService;

    @GetMapping("/users/auth/signup")
    public String getRegistrationPage(){
        return "registration";
    }

    @GetMapping("/users/auth/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/users/auth/signup")
    public String register(@RequestBody UserRegisterDTO user){
        userService.register(user);
        return "redirect:/publications";
    }

    @PostMapping("/users/auth/login") // not ready yet
    public String login(@RequestBody UserLoginDTO user){
        if (userService.login(user)){
            return "redirect:/publications";
        }
        return "redirect:/users/auth/login";
    }

    @GetMapping("/users/name/{username}")
    public ResponseEntity<List<UserDisplayDTO>> findByName(@PathVariable String username){
        return new ResponseEntity<>(userService.findUserByName(username), HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<List<UserDisplayDTO>> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }
}
