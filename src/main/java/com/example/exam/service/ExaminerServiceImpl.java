package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.RepositoryIsEmptyException;
import com.example.exam.exception.TooBigAmountException;
import com.example.exam.exception.TooSmallAmountException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final Map<String, QuestionService> services;

    public ExaminerServiceImpl(List<QuestionService> services) {
        this.services = new HashMap<>();
        for (QuestionService service:services) {
            this.services.put(service.getType(), service);
        }
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalAmount = services.get("Java").getCollectionSize();
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
                randomQuestions.add(services.get("Math").getRandomQuestion());
            }
            while (randomQuestions.size() < amount) {
                randomQuestions.add(services.get("Java").getRandomQuestion());
            }
            return randomQuestions;
        }
    }
}
