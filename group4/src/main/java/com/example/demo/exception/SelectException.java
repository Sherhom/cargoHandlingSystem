package com.example.demo.exception;

public class SelectException extends Exception{
    public SelectException(Exception e) {
        super(e);
    }

    public SelectException(String message) {
        super(message);
    }
}
