package com.roommatefinder.controllers;

import com.roommatefinder.dtos.CreateFriendshipDto;
import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.services.FriendshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<FriendshipDto>> getUserFriendships(@PathVariable Long id) {
        return ResponseEntity.ok().body(friendshipService.getUserFriendships(id));
    }

    @PostMapping()
    public ResponseEntity<FriendshipDto> createFriendship(@RequestBody CreateFriendshipDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(friendshipService.createFriendship(request));
    }

    @PutMapping()
    public ResponseEntity<FriendshipDto> updateFriendshipStatus(@RequestBody FriendshipDto request) {
        return ResponseEntity.ok().body(friendshipService.updateFriendshipStatus(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteFriendship(id);
        return ResponseEntity.noContent().build();
    }
}