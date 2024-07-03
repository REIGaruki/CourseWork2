package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.TooBigAmountException;
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

    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalAmountOfQuestions = javaQuestionService.getCollectionSize() + mathQuestionService.getCollectionSize();
        if (amount > totalAmountOfQuestions) {
            throw new TooBigAmountException("Try to get less questions, there are only " + totalAmountOfQuestions);
        } else {
            Random random = new Random();
            Set<Question> randomQuestions = new HashSet<>();
            int javaAmount = random.nextInt(totalAmountOfQuestions);
            int mathAmount = totalAmountOfQuestions - javaAmount;
            while (randomQuestions.size() < javaAmount) {
                int randomId = random.nextInt(javaQuestionService.getCollectionSize());
                randomQuestions.add(javaQuestionService.getRandomQuestion());
            }
            while (randomQuestions.size() < mathAmount) {
                int randomId = random.nextInt(mathQuestionService.getCollectionSize());
                randomQuestions.add(mathQuestionService.getRandomQuestion());
            }
            return randomQuestions;
        }
    }
}
