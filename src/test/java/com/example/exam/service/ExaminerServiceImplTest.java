package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.TooBigAmountException;
import com.example.exam.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

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
    private final Question QUESTION_3 = new Question("КОгда?",
            "Сейчас");
    private final int AMOUNT = 5;
    @Mock
    @Qualifier("JavaQuestionRepository")
    QuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    ExaminerServiceImpl sut;

    @Test
    void shouldThrowExceptionWhenAmountOfRandomQuestionsIsGreaterThanQuestionCollectionSize() {
        when(javaQuestionRepositoryMock.getCollectionSize()).thenReturn(AMOUNT - 1);
        Assertions.assertThrows(TooBigAmountException.class, () -> sut.getQuestions(AMOUNT));
    }
    @Test
    void shouldReturnAmountOfUniqueQuestions() {
        int amount = 3;
        Set<Question> expected = new HashSet<>();
        expected.add(QUESTION_1);
        expected.add(QUESTION_2);
        expected.add(QUESTION_3);
        when(javaQuestionRepositoryMock.getCollectionSize()).thenReturn(amount);
        when(javaQuestionRepositoryMock.getRandomQuestion()).thenReturn(
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