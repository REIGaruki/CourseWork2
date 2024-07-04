package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.TooBigAmountException;
import com.example.exam.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    QuestionRepository javaQuestionService;

    public ExaminerServiceImpl(QuestionRepository javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getCollectionSize()) {
            throw new TooBigAmountException("Try to get less questions, there are only "
                    + javaQuestionService.getCollectionSize());
        } else {
            Set<Question> randomQuestions = new HashSet<>();
            while (randomQuestions.size() < amount) {
                randomQuestions.add(javaQuestionService.getRandomQuestion());
            }
            return randomQuestions;
        }
    }
}
