package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.repository.JavaRepositoryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{
    JavaRepositoryService javaRepositoryService;

    @Override
    public Question add(String question, String answer) {
        return javaRepositoryService.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return javaRepositoryService.add(question);
    }

    @Override
    public Question remove(Question question) {
        return javaRepositoryService.remove(question);
    }

    @Override
    public List<Question> getAll() {
        return javaRepositoryService.getAll();
    }
    public int getCollectionSize() {
        return javaRepositoryService.getCollectionSize();
    }

    @Override
    public Question getRandomQuestion() {
        return javaRepositoryService.getRandomQuestion();
    }
}
