package com.roommatefinder.services;

import com.roommatefinder.entities.User;
import com.roommatefinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public void deleteUser(Long id)
    {
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
