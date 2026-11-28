package com.roommatefinder.controllers;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/incoming/{userId}")
    public ResponseEntity<Iterable<MessageResponse>> getIncomingMessagesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(messageService.getIncomingMessagesByUser(userId));
    }
    @GetMapping("/outgoing/{userId}")
    public ResponseEntity<Iterable<MessageResponse>> getOutgoingMessagesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(messageService.getOutgoingMessagesByUser(userId));
    }

    @PostMapping()
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody SendMessageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(request));
    }
    // could add endpoints for editing or deleting a message but not necessary.
}
