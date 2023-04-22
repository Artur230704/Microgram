package com.example.microgram.dtos.user;

import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDisplayDTO {
    private String username;
    private String email;
    private Integer publications;
    private Integer subscriptions;
    private Integer subscribers;

    public static UserDisplayDTO from(User user){
        return builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .publications(user.getPublications())
                .subscriptions(user.getSubscriptions())
                .subscribers(user.getSubscribers())
                .build();
    }
}
