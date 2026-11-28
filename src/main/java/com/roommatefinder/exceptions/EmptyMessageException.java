package com.roommatefinder.exceptions;

public class EmptyMessageException extends RuntimeException {
    public EmptyMessageException() {
        super("Cannot send an empty message.");
    }
}
