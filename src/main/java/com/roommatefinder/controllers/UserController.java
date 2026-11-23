package com.roommatefinder.controllers;


import com.roommatefinder.dtos.CreateUserRequest;
import com.roommatefinder.dtos.DeleteUserRequest;
import com.roommatefinder.dtos.UpdatePasswordRequest;
import com.roommatefinder.dtos.UserDto;
import com.roommatefinder.exceptions.UserNotFoundException;
import com.roommatefinder.mappers.UserMapper;
import com.roommatefinder.repositories.UserRepository;
import com.roommatefinder.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping()
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok().body(userService.createUser(request));
    }
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody DeleteUserRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.noContent().build();
    }
}
