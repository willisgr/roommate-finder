package com.roommatefinder.services;

import com.roommatefinder.entities.User;

import java.util.Map;

public interface ProfileMatchingService {
    // returns the map with the candidate id and the match percent as an integer.
    Map<Long, Integer> getUserMatches(User user);
    // returns a match percent as an integer between two users.
    int getUserMatch(User targetUser, User candidate);
}
