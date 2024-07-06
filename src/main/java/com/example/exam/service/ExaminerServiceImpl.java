package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.RepositoryIsEmptyException;
import com.example.exam.exception.TooBigAmountException;
import com.example.exam.exception.TooSmallAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    @Qualifier("JavaQuestionService")
    QuestionService javaQuestionService;
    @Qualifier("MathQuestionService")
    QuestionService mathQuestionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalAmount = javaQuestionService.getCollectionSize();
        if (totalAmount == 0) {
            throw new RepositoryIsEmptyException("We have no questions for you yet");
        }else if (amount <= 0) {
            throw new TooSmallAmountException("You must get at least one question");
        } else if (amount > totalAmount) {
            throw new TooBigAmountException("Try to get less questions, there are only "
                    + totalAmount);
        } else {
            int mathQuestionAmount;
            Set<Question> randomQuestions = new HashSet<>();
            Random random = new Random();
            mathQuestionAmount = random.nextInt(amount);
            while (randomQuestions.size() < mathQuestionAmount) {
                randomQuestions.add(mathQuestionService.getRandomQuestion());
            }
            while (randomQuestions.size() < amount) {
                randomQuestions.add(javaQuestionService.getRandomQuestion());
            }
            return randomQuestions;
        }
    }
}
