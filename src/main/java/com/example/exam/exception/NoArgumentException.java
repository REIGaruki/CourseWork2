package com.example.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoArgumentException extends RuntimeException{
    public NoArgumentException(String message) {
        super(message);
    }
}
