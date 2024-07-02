package com.example.exam.controller;

import com.example.exam.domain.Question;
import com.example.exam.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path="/java")
public class JavaController {
    JavaQuestionService javaQuestionService;
    @GetMapping("/add")
    public Question add(String question, String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(Question question) {
        return javaQuestionService.remove(question);
    }

    public Collection<Question> getAll() {
        return javaQuestionService.getAll();
    }

    @GetMapping("/random")
    public Question getRandomQuestion() {
        return javaQuestionService.getRandomQuestion();
    }
}
