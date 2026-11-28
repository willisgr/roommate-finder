package com.roommatefinder.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("Message not found by id: " + id);
    }
}
