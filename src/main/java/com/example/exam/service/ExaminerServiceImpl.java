package com.example.exam.service;

import com.example.exam.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    @Override
    public Collection<Question> getQuestions(int amount) {
        return null;
    }
}
