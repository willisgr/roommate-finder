package com.roommatefinder.services;

import com.roommatefinder.dtos.CreateUserRequest;
import com.roommatefinder.dtos.DeleteUserRequest;
import com.roommatefinder.dtos.UpdatePasswordRequest;
import com.roommatefinder.dtos.UserDto;
import com.roommatefinder.exceptions.UserNotFoundException;
import com.roommatefinder.exceptions.WrongPasswordException;
import com.roommatefinder.mappers.UserMapper;
import com.roommatefinder.repositories.ProfileRepository;
import com.roommatefinder.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    @Transactional
    public UserDto createUser(CreateUserRequest request) {
        var user = userMapper.toEntity(request);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(DeleteUserRequest request) {
        var userId = request.getUserId();
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        // request must match saved user password to delete
        if (! request.getPassword().equals(user.getPassword())) {
            throw new WrongPasswordException();
        }
        // Delete the user profile if one exists
        if (profileRepository.findById(userId).isPresent()) {
            profileService.deleteProfile(userId);
        }
        userRepository.delete(user);
    }

    @Transactional
    public UserDto updateUser(UserDto request) {
        var userId = request.getUserId();
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void updatePassword(UpdatePasswordRequest request) {
        var userId = request.getUserId();
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (request.getOldPassword().equals(user.getPassword())) {
            user.setPassword(request.getNewPassword());
            userRepository.save(user);
        } else {
            throw new WrongPasswordException();
        }
    }
}
