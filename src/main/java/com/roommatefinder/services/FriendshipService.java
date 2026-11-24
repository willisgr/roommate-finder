package com.roommatefinder.services;

import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.mappers.FriendshipMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendshipService {
    private final UserService userService;
    private final FriendshipMapper friendshipMapper;

    public List<FriendshipDto> getUserFriendships(Long userId) {
        var user = userService.findUser(userId);
        return friendshipMapper.toDtoList(user.getFriendshipList());
    }
}
