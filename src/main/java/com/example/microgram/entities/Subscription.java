package com.example.microgram.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private Long subscriptionId;
    private Long subscribingId;
    private Long subscribedToId;
    private LocalDate subscriptionDate;
}
