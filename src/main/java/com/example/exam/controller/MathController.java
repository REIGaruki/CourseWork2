package com.example.exam.controller;

import com.example.exam.domain.Question;
import com.example.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path="/math")
public class MathController {
    @Qualifier("mathQuestionService") QuestionService mathQuestionService;

    public MathController(QuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("/add")
    public Question add(String question, String answer) {
        return mathQuestionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(Question question) {
        return mathQuestionService.remove(question);
    }

    @GetMapping
    public Collection<Question> getAll() {
        return mathQuestionService.getAll();
    }

    @GetMapping("/random")
    public Question getRandomQuestion() {
        return mathQuestionService.getRandomQuestion();
    }
}
