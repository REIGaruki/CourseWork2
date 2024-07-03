package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.repository.MathQuestionsRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class MathQuestionService implements QuestionService{
    MathQuestionsRepository mathQuestionsRepository;
    @Override
    public Question add(String question, String answer) {
        return mathQuestionsRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return mathQuestionsRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return mathQuestionsRepository.remove(question);
    }

    @Override
    public List<Question> getAll() {
        return mathQuestionsRepository.getAll();
    }
    public int getCollectionSize() {
        return mathQuestionsRepository.getCollectionSize();
    }

    @Override
    public Question getRandomQuestion() {
        return mathQuestionsRepository.getRandomQuestion();
    }
}
