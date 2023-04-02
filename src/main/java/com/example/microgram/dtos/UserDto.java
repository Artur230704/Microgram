package com.example.microgram.dtos;

import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String password;

    private Integer publications;
    private Integer subscriptions;
    private Integer subscribers;

    public static UserDto from(User user){
        return builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .publications(user.getPublications())
                .subscriptions(user.getSubscriptions())
                .subscribers(user.getSubscribers())
                .build();
    }
}
