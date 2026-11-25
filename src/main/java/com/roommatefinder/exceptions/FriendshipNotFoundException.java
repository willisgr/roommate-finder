package com.roommatefinder.exceptions;

public class FriendshipNotFoundException extends RuntimeException {
    public FriendshipNotFoundException(Long id) {
        super("No friendship found by id: " + id);
    }
}
