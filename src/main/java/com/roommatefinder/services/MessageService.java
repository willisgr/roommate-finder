package com.roommatefinder.services;

import com.roommatefinder.dtos.MessageResponse;
import com.roommatefinder.dtos.SendMessageRequest;
import com.roommatefinder.entities.Message;
import com.roommatefinder.exceptions.UserNotFoundException;
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

        return new MessageResponse(
                savedMessage.getId(),
                savedMessage.getSender().getId(),
                savedMessage.getRecipient().getId(),
                savedMessage.getMessageContent(),
                savedMessage.getTimeSent()
        );
    }

    @Transactional
    public List<MessageResponse> getMessagesForUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<MessageResponse> responses = new ArrayList<>();
        for (Message message : messageRepository
                .findBySender_IdOrRecipient_IdOrderByTimeSentAsc(userId, userId)) {
            responses.add(new MessageResponse(
                    message.getId(),
                    message.getSender().getId(),
                    message.getRecipient().getId(),
                    message.getMessageContent(),
                    message.getTimeSent()
            ));
        }
        return responses;
    }
}
