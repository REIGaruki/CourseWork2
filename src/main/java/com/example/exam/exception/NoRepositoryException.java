package com.example.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class NoRepositoryException extends RuntimeException{
    public NoRepositoryException(String message) {
        super(message);
    }
}
