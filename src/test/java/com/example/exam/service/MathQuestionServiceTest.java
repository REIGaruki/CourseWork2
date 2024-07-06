package com.example.exam.service;

import com.example.exam.domain.Question;
import com.example.exam.exception.NoRepositoryException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MathQuestionServiceTest {
    QuestionService sut = new MathQuestionService();
    private final Question QUESTION_1 = new Question("Что?",
            "Это"
    );
    @Test
    void shouldThrowExceptionWhenAddRemoveOrGetAll() {
        Assertions.assertThrows(NoRepositoryException.class, () -> sut.add(QUESTION_1));
        Assertions.assertThrows(NoRepositoryException.class, () -> sut.remove(QUESTION_1));
        Assertions.assertThrows(NoRepositoryException.class, () -> sut.getAll());
        Assertions.assertThrows(NoRepositoryException.class, () -> sut.getCollectionSize());
    }


}