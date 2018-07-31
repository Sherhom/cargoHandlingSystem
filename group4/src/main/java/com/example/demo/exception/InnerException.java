package com.example.demo.exception;

public class InnerException extends Exception {

    public InnerException(Exception e) {
        super(e);
    }

    public InnerException(String message) {
        super(message);
    }
}
