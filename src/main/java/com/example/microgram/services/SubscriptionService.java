package com.example.microgram.services;

import com.example.microgram.daos.SubscriptionDao;
import com.example.microgram.helper.DBHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionDao subscriptionDao;
    private final DBHelper DBHelper;

    public String subscribe(String email, Long subscribedToId){
        Long userId = DBHelper.getUserIdByEmail(email);

        if (!DBHelper.getUserExistenceById(subscribedToId)){
            return "There is no user with id " + subscribedToId;
        }

        return subscriptionDao.subscribe(userId,subscribedToId);
    }
}
