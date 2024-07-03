package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.TooBigAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    Question QUESTION_1 = new Question("Что?",
            "Это"
    );
    Question QUESTION_2 = new Question("Где?",
            "Здесь"
    );
    Question QUESTION_3 = new Question("КОгда?",
            "Сейчас");
    @Mock
    JavaQuestionService javaQuestionServiceMock = new JavaQuestionService();
    @InjectMocks
    ExaminerService sut;

    @Test
    void shouldThrowExceptionWhenAmountOfRandomQuestionsIsGreaterThanQuestionCollectionSize() {
        int amount = 5;
        when(javaQuestionServiceMock.getCollectionSize()).thenReturn(amount - 1);
        Assertions.assertThrows(TooBigAmountException.class, () -> sut.getQuestions(amount));
    }
    @Test
    void shouldReturnAmountOfUniqueQuestions() {
        int amount = 3;
        Set<Question> expected = new HashSet<>();
        expected.add(QUESTION_1);
        expected.add(QUESTION_2);
        expected.add(QUESTION_3);
        when(javaQuestionServiceMock.getCollectionSize()).thenReturn(amount);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(
                QUESTION_1,
                QUESTION_1,
                QUESTION_2,
                QUESTION_2,
                QUESTION_1,
                QUESTION_3
        );
        Collection<Question> actual = sut.getQuestions(amount);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(amount, actual.size());
    }

}