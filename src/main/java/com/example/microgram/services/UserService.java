package com.example.microgram.services;

import com.example.microgram.daos.UserDao;
import com.example.microgram.dtos.UserDto;
import com.example.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String findUserByName(String username){
        List<User> users = userDao.findUserByName(username);
        return usersListToString(users);
    }

    public String findUserByEmail(String email){
        List<User> users = userDao.findUserByEmail(email);
        return usersListToString(users);
    }

    private String usersListToString(List<User> users){
        if(users.isEmpty()){
            return "There is no such a user";
        }

        StringBuilder builder = new StringBuilder();
        for (User user : users){
            builder.append("Name: " + user.getUsername() + "\n");
            builder.append("Email: " + user.getEmail() + "\n");
            builder.append("Role: " + user.getUserRole() + "\n");
            builder.append("Publications: " + user.getPublications() + "\n");
            builder.append("Subscriptions: " + user.getSubscriptions() + "\n");
            builder.append("Subscribers: " + user.getSubscribers() + "\n");
            builder.append("\n\n");
        }
        return builder.toString();
    }
}
