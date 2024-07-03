package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class JavaQuestionServiceTest {
    JavaQuestionService sut = new JavaQuestionService();

    @BeforeEach
    void fillngListBeforeAnyTest() {
        sut.javaQuestions = new ArrayList<>(Arrays.asList(
                new Question("Что такое «переменная»?",
                        "Это переменная"),
                new Question("По каким параметрам переменные различаются?",
                        "Параметры"),
                new Question("Перечислите типы переменных и действия, которые с ними можно осуществлять.",
                        "Типы и действия")
        ));
    }
    public static Stream<Arguments> provideParamsForAddQuestionTest() {
        return Stream.of(
                Arguments.of(null, "Answer"),
                Arguments.of("","Answer"),
                Arguments.of("Question", null),
                Arguments.of("Question", "")
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForAddQuestionTest")
    void shouldThrowExceptionWhenThereAreNoQuestionOrAnswer(String questi0n, String answer) {
    Assertions.assertThrows(NoArgumentException.class, () -> sut.add(questi0n, answer));
    }
    @Test
    void shouldAddQuestionCorrectly() {
        Question newQuestion = new Question("Новый вопрос", "Ответ на новый ответ");
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question("Что такое «переменная»?",
                        "Это переменная"),
                new Question("По каким параметрам переменные различаются?",
                        "Параметры"),
                new Question("Перечислите типы переменных и действия, которые с ними можно осуществлять.",
                        "Типы и действия"),
                new Question("Новый вопрос",
                        "Ответ на новый ответ")
        ));
        sut.add("Новый вопрос", "Ответ на новый ответ");
        List<Question> actual = sut.javaQuestions;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldReturnSameAddedQuestion() {
        Question expected = new Question("Новый вопрос", "Ответ на новый ответ");
        Question actual = sut.add("Новый вопрос", "Ответ на новый ответ");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveQuestionCorrectly() {
        Question removedQuestion = new Question("По каким параметрам переменные различаются?",
                "Параметры");
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question("Что такое «переменная»?",
                        "Это переменная"),
                new Question("Перечислите типы переменных и действия, которые с ними можно осуществлять.",
                        "Типы и действия")
        ));
        sut.remove(removedQuestion);
        List<Question> actual = sut.javaQuestions;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldReturnSameRemovedQuestion() {
        Question removedQuestion = new Question("Что такое «переменная»?",
                "Это переменная");
        Question expected = removedQuestion;
        Question actual = sut.remove(removedQuestion);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldThrowExceptionWhenRemoveQuestionThatDoesNotExist() {
        Question removedQuestion = new Question("Новый вопрос", "Ответ на новый ответ");
        Assertions.assertThrows(QuestionNotExistException.class, () -> sut.remove(removedQuestion));
    }

    @Test
    void shouldGetAllQuestions() {
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question("Что такое «переменная»?",
                        "Это переменная"),
                new Question("По каким параметрам переменные различаются?",
                        "Параметры"),
                new Question("Перечислите типы переменных и действия, которые с ними можно осуществлять.",
                        "Типы и действия")
        ));
        List<Question> actual = sut.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnQuantityOfQuestions() {
        int expected = 3;
        int actual = sut.getCollectionSize();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnRandomQuestionFromPoolOfJavaQuestions() {
        Question randomQuestion = sut.getRandomQuestion();
        Assertions.assertTrue(sut.javaQuestions.contains(randomQuestion));
    }
}