package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class MessageResponse {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private String messageContent;
    private Instant timeSent;
}
