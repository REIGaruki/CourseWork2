package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionAlreadyExistsException;
import com.example.exam.exception.QuestionNotExistException;
import com.example.exam.exception.RepositoryIsEmptyException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value= "javaQuestionService")
public class JavaQuestionService implements QuestionService{
    private List<Question> javaQuestions = new ArrayList<>();
    private final Random random = new Random();

    public JavaQuestionService() {
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
        if (question == null || answer == null || question.equals("") || answer.equals("")) {
            throw new NoArgumentException("Absence of question or answer");
        }
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (getAll().contains(question)) {
            throw new QuestionAlreadyExistsException("Question already exists");
        }
        javaQuestions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!getAll().contains(question)) {
            throw new QuestionNotExistException("Question does not exist");
        }
        javaQuestions.remove(question);
        return question;
    }

    @Override
    public List<Question> getAll() {
        return this.javaQuestions;
    }

    @Override
    public Question getRandomQuestion() {
        if (getAll().isEmpty()) {
            throw new RepositoryIsEmptyException("There are no questions for you yet");
        }
        int randomId = random.nextInt(javaQuestions.size());
        return javaQuestions.get(randomId);
    }
}