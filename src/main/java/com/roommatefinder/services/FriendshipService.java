package com.roommatefinder.services;

import com.roommatefinder.dtos.CreateFriendshipDto;
import com.roommatefinder.dtos.FriendshipDto;
import com.roommatefinder.entities.Friendship;
import com.roommatefinder.exceptions.FriendshipAlreadyExistsException;
import com.roommatefinder.exceptions.FriendshipNotFoundException;
import com.roommatefinder.mappers.FriendshipMapper;
import com.roommatefinder.repositories.FriendshipRepository;
import com.roommatefinder.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendshipService {
    private final UserService userService;
    private final FriendshipMapper friendshipMapper;
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public List<FriendshipDto> getUserFriendships(Long userId) {
        var user = userService.findUser(userId);
        return friendshipMapper.toDtoList(user.getFriendshipList());
    }

    @Transactional
    public FriendshipDto createFriendship(CreateFriendshipDto request) {
        // check if both users exist or throw not found exception
        var user = userService.findUser(request.getUserId());
        userService.findUser(request.getFriendId());

        // check if the friendship to create already exists
        if (friendshipRepository.existsByUserIdAndFriendId(request.getUserId(), request.getFriendId())) {
            throw new FriendshipAlreadyExistsException(request.getUserId(), request.getFriendId());
        }
        if (friendshipRepository.existsByUserIdAndFriendId(request.getFriendId(), request.getUserId())) {
            throw new FriendshipAlreadyExistsException(request.getFriendId(), request.getUserId());
        }

        var friendship = friendshipMapper.toEntity(request);
        user.addFriendship(friendship);
        userRepository.save(user);
        return friendshipMapper.toDto(friendship);
    }

    @Transactional
    public FriendshipDto updateFriendshipStatus(FriendshipDto request) {
        var friendship = findFriendship(request.getId());
        friendship.setStatus(request.getStatus());
        var saved = friendshipRepository.save(friendship);
        return friendshipMapper.toDto(saved);
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.delete(findFriendship(id));
    }

    public Friendship findFriendship(Long id) {
        return friendshipRepository.findById(id).orElseThrow(() ->
                new FriendshipNotFoundException(id));
    }


}
