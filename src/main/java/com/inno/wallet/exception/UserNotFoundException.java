package com.inno.wallet.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { super(message); }
}
