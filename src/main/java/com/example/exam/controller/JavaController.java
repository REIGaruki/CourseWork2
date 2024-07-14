package com.example.exam.controller;

import com.example.exam.domain.Question;
import com.example.exam.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path="java")
public class JavaController {
    private final QuestionService javaQuestionService;

    public JavaController(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam(required = false) String question,
                        @RequestParam(required = false) String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam(required = false) String question,
                           @RequestParam(required = false) String answer) {
        return javaQuestionService.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getAll() {
        return javaQuestionService.getAll();
    }
}
