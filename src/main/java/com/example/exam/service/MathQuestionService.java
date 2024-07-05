package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Qualifier("MathQuestionService")
public class MathQuestionService implements QuestionService{
    private QuestionRepository mathQuestionRepository;

    public MathQuestionService(QuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return mathQuestionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return mathQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }
    @Override
    public int getCollectionSize() {
        return mathQuestionRepository.getCollectionSize();
    }

    @Override
    public Question getRandomQuestion() {
        return mathQuestionRepository.getRandomQuestion();
    }
}
