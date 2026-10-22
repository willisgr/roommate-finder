package com.roommatefinder.controllers;

import com.roommatefinder.entities.Message;
import com.roommatefinder.repositories.MessageRepository;
import com.roommatefinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @GetMapping()
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageByUserId(Long id) {
        
        return messageRepository.findById(id);
    }
}
