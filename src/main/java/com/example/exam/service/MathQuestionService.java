package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
@Qualifier("mathQuestionService")
public class MathQuestionService implements QuestionService{

    public MathQuestionService() {
    }

    @Override
    public Question add(String question, String answer) {
        throw new NoRepositoryException("Method not allowed");
    }

    @Override
    public Question add(Question question) {
        throw new NoRepositoryException("Method not allowed");
    }

    @Override
    public Question remove(Question question) {
        throw new NoRepositoryException("Method not allowed");
    }

    @Override
    public Collection<Question> getAll() {
        throw new NoRepositoryException("Method not allowed");
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        char maths[] = {'+', '-', '*', '/'};
        int a = random.nextInt(1000) + 1;
        int b = random.nextInt(1000) + 1;
        int c = random.nextInt(4);
        int d = 0;
        char math = maths[c];
        String question = String.valueOf(a) + ' ' + maths[c] + ' ' + String.valueOf(b) + " = ?";
        switch (c) {
            case 0:
                d=a+b;
                break;
            case 1:
                d=a-b;
                break;
            case 2:
                d=a*b;
                break;
            case 3:
                d=a/b;
                break;
        }
        String answer = String.valueOf(d);
        return new Question(question, answer);
    }
}
