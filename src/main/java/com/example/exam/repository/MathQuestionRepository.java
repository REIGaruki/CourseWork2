package com.example.exam.repository;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionAlreadyExistsException;
import com.example.exam.exception.QuestionNotExistException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
@Qualifier("MathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository{
    private List<Question> mathQuestions;

    public MathQuestionRepository(List<Question> javaQuestions) {
        this.mathQuestions = javaQuestions;
    }

    @PostConstruct
    public void init() {
        this.mathQuestions = new ArrayList<>(Arrays.asList(
                new Question("2*2?",
                        "4"),
                new Question("2/5?",
                        "0.4"),
                new Question("5+3?",
                        "8"),
                new Question("9-6?",
                        "3"),
                new Question("7*8?",
                        "56"),
                new Question("17*3",
                        "51"),
                new Question("63-13?",
                        "50"),
                new Question("777/111?",
                        "7"),
                new Question("99+1",
                        "100"),
                new Question("789/789?",
                        "1")
        ));

    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null || question == "" || answer == "") {
            throw new NoArgumentException("Absence of question or answer");
        } else if (getAll().contains(new Question(question, answer))) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            Question newQuestion = new Question(question, answer);
            mathQuestions.add(newQuestion);
            return newQuestion;
        }
    }

    @Override
    public Question add(Question question) {
        if (getAll().contains(question)) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            mathQuestions.add(question);
            return question;
        }
    }

    @Override
    public Question remove(Question question) {
        if (!getAll().contains(question)) {
            throw new QuestionNotExistException("Question does not exist");
        } else {
            mathQuestions.remove(question);
            return question;
        }
    }

    @Override
    public List<Question> getAll() {
        return this.mathQuestions;
    }
    @Override
    public int getCollectionSize() {
        return mathQuestions.size();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomId = random.nextInt(getCollectionSize());
        return mathQuestions.get(randomId);
    }
}
