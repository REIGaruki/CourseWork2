package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.RepositoryIsEmptyException;
import com.example.exam.exception.TooBigAmountException;
import com.example.exam.exception.TooSmallAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private final Question QUESTION_1 = new Question("Что?",
            "Это"
    );
    private final Question QUESTION_2 = new Question("Где?",
            "Здесь"
    );
    private final Question QUESTION_3 = new Question("Когда?",
            "Сейчас");
    private final Question QUESTION_4 = new Question("Q4?",
            "A4"
    );
    private final Question QUESTION_5 = new Question("Q5?",
            "A5"
    );
    private final Question QUESTION_6 = new Question("Q6?",
            "A6");
    private final Question QUESTION_7 = new Question("Q7?",
            "A7");
    private final int JAVA_AMOUNT = 4;
    private final int MATH_AMOUNT = 3;
    private final int TOTAL_AMOUNT = JAVA_AMOUNT + MATH_AMOUNT;
    private final int ERROR_AMOUNT = TOTAL_AMOUNT + 1;
    private List<Question> JAVA_QUESTION_LIST = new ArrayList<>();
    private List<Question> MATH_QUESTION_LIST = new ArrayList<>();

    @Mock
    JavaQuestionService javaQuestionServiceMock;
    @Mock
    MathQuestionService mathQuestionServiceMock;

    ExaminerServiceImpl sut;

    @BeforeEach
    void initSut() {
        List<QuestionService> services = List.of(mathQuestionServiceMock, javaQuestionServiceMock);
        sut = new ExaminerServiceImpl(services);
        JAVA_QUESTION_LIST.add(QUESTION_1);
        JAVA_QUESTION_LIST.add(QUESTION_2);
        JAVA_QUESTION_LIST.add(QUESTION_3);
        JAVA_QUESTION_LIST.add(QUESTION_4);
    }

    @Test
    @DisplayName("")
    void shouldThrowExceptionWhenAmountOfRandomQuestionsIsGreaterThanQuestionCollectionSizeOrNegativeOrZero() {
        Random random = new Random();
        int randomNegativeNumberOrZero = random.nextInt(Integer.MAX_VALUE) * (-1);
        when(javaQuestionServiceMock.getAll()).thenReturn(JAVA_QUESTION_LIST);
        Assertions.assertThrows(TooBigAmountException.class, () -> sut.getQuestions(ERROR_AMOUNT));
        Assertions.assertThrows(TooSmallAmountException.class,
                () -> sut.getQuestions(randomNegativeNumberOrZero));
    }
    @Test
    void shouldThrowExceptionWhenThereAreNoQuestions() {
        when(javaQuestionServiceMock.getAll()).thenReturn(null);
        Random random = new Random();
        int randomPositiveNumber = random.nextInt(Integer.MAX_VALUE) + 1;
        Assertions.assertThrows(RepositoryIsEmptyException.class,
                () -> sut.getQuestions(randomPositiveNumber));
    }
    @Test
    void shouldReturnAmountOfUniqueQuestions() {
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(
                QUESTION_1,
                QUESTION_2,
                QUESTION_3,
                QUESTION_4
        );
        when(mathQuestionServiceMock.getRandomQuestion()).thenReturn(
                QUESTION_5,
                QUESTION_6,
                QUESTION_7
        );
        when(javaQuestionServiceMock.getAll()).thenReturn(JAVA_QUESTION_LIST);
        Set<Question> expected = new HashSet<>();
        expected.add(QUESTION_1);
        expected.add(QUESTION_2);
        expected.add(QUESTION_3);
        expected.add(QUESTION_4);
        expected.add(QUESTION_5);
        expected.add(QUESTION_6);
        expected.add(QUESTION_7);
        Collection<Question> actual = sut.getQuestions(TOTAL_AMOUNT);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(TOTAL_AMOUNT, actual.size());
    }

}