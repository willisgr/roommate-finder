package com.roommatefinder.controllers;

import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.services.FriendshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<FriendshipDto>> getUserFriendships(@PathVariable Long id) {
        return ResponseEntity.ok().body(friendshipService.getUserFriendships(id));
    }
}