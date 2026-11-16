package com.roommatefinder.exceptions;

/**
 * Thrown when creating a profile for a user who already has one.
 */
public class ProfileAlreadyExistsException extends RuntimeException {
    public ProfileAlreadyExistsException(Long userId) {
        super("A profile already exists for user id: " + userId);
    }
}
