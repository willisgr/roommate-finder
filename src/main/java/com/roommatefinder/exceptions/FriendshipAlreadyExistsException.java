package com.roommatefinder.exceptions;

public class FriendshipAlreadyExistsException extends RuntimeException {
    public FriendshipAlreadyExistsException(Long userId, Long friendId) {
        super("A friendship already exists for user: " + userId + " and " + friendId);
    }
}
