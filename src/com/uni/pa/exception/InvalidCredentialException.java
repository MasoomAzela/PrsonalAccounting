package com.uni.pa.exception;

public class InvalidCredentialException extends Exception {

    public InvalidCredentialException() {

    }

    public InvalidCredentialException(String message) {
        super(message);
    }

    public InvalidCredentialException(Exception ex) {
        super(ex);
    }

    public InvalidCredentialException(Exception ex, String message) {
        super(message, ex);
    }

}
