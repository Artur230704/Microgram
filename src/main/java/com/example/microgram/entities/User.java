package com.example.microgram.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    private Boolean enabled;
    private Integer publications;
    private Integer subscriptions;
    private Integer subscribers;

}
