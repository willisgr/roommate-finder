package com.roommatefinder.controllers;

import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.services.FriendshipService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @GetMapping
    public List<FriendshipDto> getFriendsList() {
        return friendshipService.getAllFriendships();
    }
}