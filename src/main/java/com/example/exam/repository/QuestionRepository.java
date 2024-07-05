package com.example.exam.repository;

import com.example.exam.domain.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
    int getCollectionSize();
}