package com.example.demo.exception;

public class UpdateException extends Exception {
    public UpdateException(Exception e) {
        super(e);
    }

    public UpdateException(String message) {
        super(message);
    }
}
