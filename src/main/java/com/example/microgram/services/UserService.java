package com.example.microgram.services;

import com.example.microgram.daos.UserDao;
import com.example.microgram.dtos.UserDto;
import com.example.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    public String register(UserDto userDto){
        if (userDto.getUsername() == null){
            return "Username can not be empty";
        }
        if (userDto.getEmail() == null){
            return "User email can not be empty";
        }
        if (userDto.getPassword() == null){
            return "User password can not be empty";
        }
        return userDao.register(userDto);
    }

    public List<UserDto> findUserByName(String username){
        List<User> users = userDao.findUserByName(username);
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public List<UserDto> findUserByEmail(String email){
        List<User> users = userDao.findUserByEmail(email);
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
