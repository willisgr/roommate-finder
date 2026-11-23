package com.roommatefinder.services;

import com.roommatefinder.dtos.UpdatePasswordRequest;
import com.roommatefinder.entities.User;
import com.roommatefinder.exceptions.UserNotFoundException;
import com.roommatefinder.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id)
    {
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Transactional
    public ResponseEntity<Void> updatePassword(UpdatePasswordRequest request) {
        var userId = request.getUserId();
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (request.getOldPassword().equals(user.getPassword())) {
            user.setPassword(request.getNewPassword());
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
