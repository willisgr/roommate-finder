package com.roommatefinder.services;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.entities.Message;
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
    private final UserRepository userRepository;
    private final UserService userService;
    private final MessageMapper messageMapper;

    @Transactional
    public MessageResponse sendMessage(SendMessageRequest request) {
        if (request.getMessageContent() == null || request.getMessageContent().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Message content cannot be empty"
            );
        }

        var sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new UserNotFoundException(request.getSenderId()));

        var recipient = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new UserNotFoundException(request.getReceiverId()));

        var message = Message.builder()
                .sender(sender)
                .recipient(recipient)
                .messageContent(request.getMessageContent())
                .timeSent(Instant.now())
                .build();
        var savedMessage = messageRepository.save(message);

        return toResponse(savedMessage);
    }

    public List<MessageResponse> getIncomingMessagesByUser(Long userId) {
        var user = userService.findUser(userId);
        return messageMapper.toDtoList(user.getIncomingMessages());
    }
    public List<MessageResponse> getOutgoingMessagesByUser(Long userId) {
        var user = userService.findUser(userId);
        return messageMapper.toDtoList(user.getOutgoingMessages());
    }


    private MessageResponse toResponse(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getSender().getId(),
                message.getRecipient().getId(),
                message.getMessageContent(),
                message.getTimeSent()
        );
    }


}

