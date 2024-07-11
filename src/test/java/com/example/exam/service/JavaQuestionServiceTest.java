package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    @Qualifier("JavaQuestionRepository")
    QuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    JavaQuestionService sut;
    private final Question QUESTION_1 = new Question("Что?",
            "Это"
    );
    private final Question QUESTION_2 = new Question("Где?",
            "Здесь"
    );
    private final Question QUESTION_3 = new Question("КОгда?",
            "Сейчас");
    private final int SIZE = 5;
    @Test
    void shouldAddToRepositoryCorrectly() {
        when(javaQuestionRepositoryMock.add(QUESTION_1)).thenReturn(QUESTION_1);
        Question expected = QUESTION_1;
        Question actual = sut.add(QUESTION_1);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldRemoveFromRepositoryCorrectly() {
        when(javaQuestionRepositoryMock.remove(QUESTION_1)).thenReturn(QUESTION_1);
        Question expected = QUESTION_1;
        Question actual = sut.remove(QUESTION_1);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldGetRandomQuestionFromRepositoryCorrectly() {
        when(javaQuestionRepositoryMock.getRandomQuestion()).thenReturn(QUESTION_1);
        Question expected = QUESTION_1;
        Question actual = sut.getRandomQuestion();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldGetAllQuestionsFromRepositoryCorrectly() {
        Collection<Question> expected = new ArrayList<>(Arrays.asList(
                QUESTION_1, QUESTION_2, QUESTION_3
        ));
        when(javaQuestionRepositoryMock.getAll()).thenReturn(expected);
        Collection<Question> actual = sut.getAll();
        Assertions.assertEquals(expected,actual);
    }

}