package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendMessageRequest {
    private Long senderId;
    private Long receiverId;
    private String messageContent;
}
