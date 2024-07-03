package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionAlreadyExistsException;
import com.example.exam.exception.QuestionNotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService{
    public static List<Question> javaQuestions = new ArrayList<>();

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null || question == "" || answer == "") {
            throw new NoArgumentException("Absence of question or answer");
        } else if (javaQuestions.contains(new Question(question, answer))) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            Question newQuestion = new Question(question, answer);
            javaQuestions.add(newQuestion);
            return newQuestion;
        }
    }

    @Override
    public Question add(Question question) {
        if (javaQuestions.contains(question)) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            javaQuestions.add(question);
            return question;
        }
    }

    @Override
    public Question remove(Question question) {
        if (!javaQuestions.contains(question)) {
            throw new QuestionNotExistException("Question does not exist");
        } else {
            javaQuestions.remove(question);
            return question;
        }
    }

    @Override
    public List<Question> getAll() {
        return javaQuestions;
    }
    public int getCollectionSize() {
        return javaQuestions.size();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomId = random.nextInt(getCollectionSize());
        return javaQuestions.get(randomId);
    }
}
