package com.uni.pa.exception;

public class InvalidUsernameException extends Exception {
    public InvalidUsernameException() {

    }

    public InvalidUsernameException(String message) {
        super(message);
    }

    public InvalidUsernameException(Exception ex) {
        super(ex);
    }

    public InvalidUsernameException(Exception ex, String message) {
        super(message, ex);
    }
}
