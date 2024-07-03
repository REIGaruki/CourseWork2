package com.example.exam.repository;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionAlreadyExistsException;
import com.example.exam.exception.QuestionNotExistException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MathQuestionsRepository implements QuestionReopsitory{
    public static List<Question> questions;
    @Override
    public void init() {
        questions = new ArrayList<>(Arrays.asList(
                new Question("Дважды два?", "Четыре"),
                new Question("Дважды три?", "Шесть"),
                new Question("Дважды четыре?", "Восемь"),
                new Question("Пятью пять?", "Двадцать пять"),
                new Question("Шестью шесть?", "Тридцать шесть"),
                new Question("Шестью восемь?", "Сорок восемь"),
                new Question("Делить на ноль?", "Нельзя"),
                new Question("Три в квадрате?", "Девять")
        ));
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null || question == "" || answer == "") {
            throw new NoArgumentException("Absence of question or answer");
        } else if (questions.contains(new Question(question, answer))) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            Question newQuestion = new Question(question, answer);
            questions.add(newQuestion);
            return newQuestion;
        }
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            questions.add(question);
            return question;
        }
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotExistException("Question does not exist");
        } else {
            questions.remove(question);
            return question;
        }
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }
    public int getCollectionSize() {
        return questions.size();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomId = random.nextInt(getCollectionSize());
        return questions.get(randomId);
    }
}
