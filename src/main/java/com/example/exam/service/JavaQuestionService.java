package com.example.exam.service;

import com.example.exam.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JavaQuestionService implements QuestionService{
    @Override
    public Question add(String question, String answer) {
        return null;
    }

    @Override
    public Question add(Question question) {
        return null;
    }

    @Override
    public Question remove(Question question) {
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return null;
    }

    @Override
    public Question getRandomQuestion() {
        return null;
    }
}
