package com.roommatefinder.controllers;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/user/{userId}")
    public List<MessageResponse> getMessagesForUser(@PathVariable Long userId) {
        return messageService.getMessagesForUser(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse sendMessage(@RequestBody SendMessageRequest request) {
        return messageService.sendMessage(request);
    }
}
