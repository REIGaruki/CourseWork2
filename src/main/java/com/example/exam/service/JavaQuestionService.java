package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("JavaQuestionService")
public class JavaQuestionService implements QuestionService{
    private QuestionRepository javaQuestionRepository;

    public JavaQuestionService(QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return javaQuestionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }
    @Override
    public int getCollectionSize() {
        return javaQuestionRepository.getCollectionSize();
    }

    @Override
    public Question getRandomQuestion() {
        return javaQuestionRepository.getRandomQuestion();
    }
}
