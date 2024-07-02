package com.example.exam.service;

import com.example.exam.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    JavaQuestionService javaQuestionService;
    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getCollectionSize()) {
            throw new IndexOutOfBoundsException();
        } else {
            Random random = new Random();
            Set<Question> randomQuestions = new HashSet<>();
            while (randomQuestions.size() < amount) {
                int randomId = random.nextInt(javaQuestionService.getCollectionSize());
                randomQuestions.add(javaQuestionService.getRandomQuestion());
            }
            return randomQuestions;
        }
    }
}
