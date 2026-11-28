package com.roommatefinder.services;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.entities.Message;
import com.roommatefinder.exceptions.EmptyMessageException;
import com.roommatefinder.exceptions.UserNotFoundException;
import com.roommatefinder.mappers.MessageMapper;
import com.roommatefinder.repositories.MessageRepository;
import com.roommatefinder.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;

    @Transactional
    public MessageResponse sendMessage(SendMessageRequest request) {
        if (request.getMessageContent() == null || request.getMessageContent().isBlank()) {
            throw new EmptyMessageException();
        }
        var sender = userService.findUser(request.getSenderId());
        var recipient = userService.findUser(request.getRecipientId());

        var message = messageMapper.toEntity(request);
        sender.sendMessage(recipient, message);
        userRepository.save(sender);
        userRepository.save(recipient);

        return messageMapper.toDto(message);
    }

    public List<MessageResponse> getIncomingMessagesByUser(Long userId) {
        var user = userService.findUser(userId);
        return messageMapper.toDtoList(user.getIncomingMessages());
    }
    public List<MessageResponse> getOutgoingMessagesByUser(Long userId) {
        var user = userService.findUser(userId);
        return messageMapper.toDtoList(user.getOutgoingMessages());
    }

}

