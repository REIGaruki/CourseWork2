package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.RepositoryIsEmptyException;
import com.example.exam.exception.TooBigAmountException;
import com.example.exam.exception.TooSmallAmountException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService javaService;

    public ExaminerServiceImpl(QuestionService javaService) {
        this.javaService = javaService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new TooSmallAmountException("You must get at least one question");
        }
        if (javaService.getAll().isEmpty()) {
            throw new RepositoryIsEmptyException("We have no questions for you yet");
        }
        if (amount > javaService.getAll().size()) {
            throw new TooBigAmountException("Try to get less questions, there are only "
                    + javaService.getAll().size());
        }
        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            randomQuestions.add(javaService.getRandomQuestion());
        }
        return randomQuestions;
    }

}

