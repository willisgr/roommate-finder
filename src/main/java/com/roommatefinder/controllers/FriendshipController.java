package com.roommatefinder.controllers;

import com.roommatefinder.entities.Friendship;
import com.roommatefinder.entities.User;
import com.roommatefinder.repositories.FriendshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipRepository friendshipRepository;

    @GetMapping()
    public Iterable<Friendship> getFriendsList() {
        return friendshipRepository.findAll();
    }
}
