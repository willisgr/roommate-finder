package com.roommatefinder.services;

import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.entities.Friendship;
import com.roommatefinder.repositories.FriendshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;

    @Transactional(readOnly = true)
    public List<FriendshipDto> getAllFriendships() {
        List<FriendshipDto> friendships = new ArrayList<>();
        for (Friendship friendship : friendshipRepository.findAll()) {
            friendships.add(new FriendshipDto(
                    friendship.getId(),
                    friendship.getUser() != null ? friendship.getUser().getId() : null,
                    friendship.getUser() != null ? friendship.getUser().getUsername() : null,
                    friendship.getFriend() != null ? friendship.getFriend().getId() : null,
                    friendship.getFriend() != null ? friendship.getFriend().getUsername() : null,
                    friendship.getStatus()
            ));
        }
        return friendships;
    }
}