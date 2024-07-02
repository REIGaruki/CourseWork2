package com.example.exam.controller;

import com.example.exam.domain.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {
    ExamController examController;
    @GetMapping("/get/{amount}")
    Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examController.getQuestions(amount);
    }
}
