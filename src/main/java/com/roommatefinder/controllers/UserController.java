package com.roommatefinder.controllers;


import com.roommatefinder.dtos.CreateUserRequest;
import com.roommatefinder.entities.User;
import com.roommatefinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping()
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(user -> new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()))
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail());
        
        return ResponseEntity.ok(userDto);
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        var user = new User();
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setPassword(request.password);
        User saved = userRepository.save(user);

        var userDto = new UserDto(saved.getId(), saved.getUsername(), saved.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
