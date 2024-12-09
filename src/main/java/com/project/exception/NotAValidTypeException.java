package com.project.exception;

public class NotAValidTypeException extends RuntimeException {
    public NotAValidTypeException(String message) {
        super(message);
    }
}
