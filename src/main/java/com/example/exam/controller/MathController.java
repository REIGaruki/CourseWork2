package com.example.exam.controller;

import com.example.exam.domain.Question;
import com.example.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path="/math")
public class MathController {
    QuestionService questionService;
    @Autowired
    public MathController(@Qualifier("MathQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping("/add")
    public Question add(String question, String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(Question question) {
        return questionService.remove(question);
    }

    public Collection<Question> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/random")
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }
}
