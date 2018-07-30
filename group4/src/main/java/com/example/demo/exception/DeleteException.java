package com.example.demo.exception;

public class DeleteException extends Exception {
    public DeleteException(Exception e) {
        super(e);
    }

    public DeleteException(String message) {
        super(message);
    }
}
