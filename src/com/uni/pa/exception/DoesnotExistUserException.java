package com.uni.pa.exception;

public class DoesnotExistUserException extends Exception {

    public DoesnotExistUserException() {

    }

    public DoesnotExistUserException(String message) {
        super(message);
    }

    public DoesnotExistUserException(Exception ex) {
        super(ex);
    }

    public DoesnotExistUserException(Exception ex, String message) {
        super(message, ex);
    }

}