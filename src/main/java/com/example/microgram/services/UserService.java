package com.example.microgram.services;

import com.example.microgram.daos.UserDao;
import com.example.microgram.dtos.user.UserDisplayDTO;
import com.example.microgram.dtos.user.UserLoginDTO;
import com.example.microgram.dtos.user.UserRegisterDTO;
import com.example.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    public void register(UserRegisterDTO user){
        userDao.register(user);
    }

    public List<UserDisplayDTO> findUserByName(String username){
        List<User> users = userDao.findUserByName(username);
        return users.stream()
                .map(UserDisplayDTO::from)
                .collect(Collectors.toList());
    }

    public List<UserDisplayDTO> findUserByEmail(String email){
        List<User> users = userDao.findUserByEmail(email);
        return users.stream()
                .map(UserDisplayDTO::from)
                .collect(Collectors.toList());
    }

    public boolean login(UserLoginDTO user){
        return userDao.login(user);
    }
}
