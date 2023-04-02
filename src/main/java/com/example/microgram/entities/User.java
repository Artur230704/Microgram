package com.example.microgram.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String userRole;
    private Integer publications;
    private Integer subscriptions;
    private Integer subscribers;
}
