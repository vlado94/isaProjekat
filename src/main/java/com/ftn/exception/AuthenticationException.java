package com.ftn.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
