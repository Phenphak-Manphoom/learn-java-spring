package com.example.demo.backend.exception;

public abstract class BaseException extends Exception {
    public BaseException(String code) {
        super(code);
    }
}
