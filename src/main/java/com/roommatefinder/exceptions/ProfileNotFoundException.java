package com.roommatefinder.exceptions;

/**
 * Thrown when a profile cannot be found for the given user id.
 */
public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Long userId) {
        super("Profile not found for user id: " + userId);
    }
}
