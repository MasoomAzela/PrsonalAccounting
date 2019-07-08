package com.uni.pa.exception;

public class UserIsDeactiveException extends Exception {

    public UserIsDeactiveException() {

    }

    public UserIsDeactiveException(String message) {
        super(message);
    }

    public UserIsDeactiveException(Exception ex) {
        super(ex);
    }

    public UserIsDeactiveException(Exception ex, String message) {
        super(message, ex);
    }

}
