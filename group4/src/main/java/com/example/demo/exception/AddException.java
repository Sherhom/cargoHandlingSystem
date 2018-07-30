package com.example.demo.exception;

public class AddException extends Exception {
    public AddException(Exception e) {
        super(e);
    }

    public AddException(String message) {
        super(message);
    }
}
