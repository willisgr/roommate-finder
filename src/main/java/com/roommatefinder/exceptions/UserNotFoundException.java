package com.roommatefinder.exceptions;

/**
 * Thrown when a user cannot be found in the database.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }
}
