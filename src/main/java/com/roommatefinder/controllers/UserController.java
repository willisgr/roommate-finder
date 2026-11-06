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
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<CreateUserRequest> createUser(@RequestBody CreateUserRequest request) {
        var user = new User();
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setPassword(request.password);
        User saved = userRepository.save(user);

        var response = new CreateUserRequest(saved.getUsername(), saved.getEmail(), saved.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
