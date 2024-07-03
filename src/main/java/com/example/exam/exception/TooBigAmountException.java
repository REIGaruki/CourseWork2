package com.example.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooBigAmountException extends RuntimeException{
    public TooBigAmountException(String message) {
        super(message);
    }
}
