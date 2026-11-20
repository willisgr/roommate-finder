package com.roommatefinder.controllers;

import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.mappers.FriendshipMapper;
import com.roommatefinder.repositories.FriendshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;

    @GetMapping()
    public Iterable<FriendshipDto> getFriendsList() {
        return friendshipRepository.findAll()
                .stream()
                .map(friendshipMapper::toDto)
                .toList();
    }
}