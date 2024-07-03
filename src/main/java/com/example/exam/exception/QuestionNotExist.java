package com.example.exam.exception;

public class QuestionNotExist extends RuntimeException{
    public QuestionNotExist(String message) {
        super(message);
    }
}
