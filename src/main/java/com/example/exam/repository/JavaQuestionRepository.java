package com.example.exam.repository;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionAlreadyExistsException;
import com.example.exam.exception.QuestionNotExistException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository{
    private List<Question> javaQuestions;

    public JavaQuestionRepository(List<Question> javaQuestions) {
        this.javaQuestions = javaQuestions;
    }

    @PostConstruct
    public void init() {
        this.javaQuestions = new ArrayList<>(Arrays.asList(
                new Question("Что такое «переменная»?",
                        "Это переменная"),
                new Question("По каким параметрам переменные различаются?",
                        "Параметры"),
                new Question("Перечислите типы переменных и действия, которые с ними можно осуществлять.",
                        "Типы и действия"),
                new Question("Что означает \"инициализация\"?",
                        "Означает инициалтзацию"),
                new Question("Какие особенности инициализации вы можете назвать?",
                        "Никакие"),
                new Question("Какие условные операторы вы знаете? Дайте краткое определение каждому из них.",
                        "Краткие определения"),
                new Question("Что такое \"цикл\"?",
                        "Это цикл"),
                new Question("Ка. они разныекие циклы вы знаете, в чем их отличия?",
                        "Знаю все"),
                new Question("Что вы знаете о массивах?",
                        "ЧТо они масивные"),
                new Question("Что такое \"метод\"?",
                        "Сериал")
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
            javaQuestions.add(newQuestion);
            return newQuestion;
        }
    }

    @Override
    public Question add(Question question) {
        if (getAll().contains(question)) {
            throw new QuestionAlreadyExistsException("Question already exists");
        } else {
            javaQuestions.add(question);
            return question;
        }
    }

    @Override
    public Question remove(Question question) {
        if (!getAll().contains(question)) {
            throw new QuestionNotExistException("Question does not exist");
        } else {
            javaQuestions.remove(question);
            return question;
        }
    }

    @Override
    public List<Question> getAll() {
        return this.javaQuestions;
    }
    @Override
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
