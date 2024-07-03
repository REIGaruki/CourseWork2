package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoArgumentException;
import com.example.exam.exception.QuestionAlreadyExistsException;
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
    String QUESTION_1 = "Что такое «переменная»?";
    String ANSWER_1 = "Это переменная";
    String QUESTION_2 = "По каким параметрам переменные различаются?";
    String ANSWER_2 = "Параметры";
    String QUESTION_3 = "Перечислите типы переменных и действия, которые с ними можно осуществлять.";
    String ANSWER_3 = "Типы и действия";
    String QUESTION_4 = "Новый вопрос";
    String ANSWER_4 = "Новый ответ";


    @BeforeEach
    void fillngListBeforeAnyTest() {
        sut.javaQuestions = new ArrayList<>(Arrays.asList(
                new Question(QUESTION_1, ANSWER_1),
                new Question(QUESTION_2, ANSWER_2),
                new Question(QUESTION_3, ANSWER_3)
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
        Question newQuestion = new Question(QUESTION_4, ANSWER_4);
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question(QUESTION_1, ANSWER_1),
                new Question(QUESTION_2, ANSWER_2),
                new Question(QUESTION_3, ANSWER_3),
                new Question(QUESTION_4, ANSWER_4)
        ));
        sut.add(QUESTION_4, ANSWER_4);
        List<Question> actual = sut.javaQuestions;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldReturnSameAddedQuestion() {
        Question expected = new Question(QUESTION_4, ANSWER_4);
        Question actual = sut.add(QUESTION_4, ANSWER_4);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shoudThrowExceptionIfQuestionIsAlreadyAdded() {
        Assertions.assertThrows(QuestionAlreadyExistsException.class,
                () -> sut.add(
                        new Question(QUESTION_1, ANSWER_1)
                ));
    }

    @Test
    void shouldRemoveQuestionCorrectly() {
        Question removedQuestion = new Question(QUESTION_2, ANSWER_2);
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question(QUESTION_1, ANSWER_1),
                new Question(QUESTION_3, ANSWER_3)
        ));
        sut.remove(removedQuestion);
        List<Question> actual = sut.javaQuestions;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldReturnSameRemovedQuestion() {
        Question removedQuestion = new Question(QUESTION_1, ANSWER_1);
        Question expected = removedQuestion;
        Question actual = sut.remove(removedQuestion);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldThrowExceptionWhenRemoveQuestionThatDoesNotExist() {
        Question removedQuestion = new Question(QUESTION_4, QUESTION_4);
        Assertions.assertThrows(QuestionNotExistException.class, () -> sut.remove(removedQuestion));
    }

    @Test
    void shouldGetAllQuestions() {
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question(QUESTION_1, ANSWER_1),
                new Question(QUESTION_2, ANSWER_2),
                new Question(QUESTION_3, ANSWER_3)
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