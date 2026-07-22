package com.example.demo.service;

import com.example.demo.entity.Question;


import java.util.List;

public interface QuestionService {
    public List<Question> getAllQuestions();

    public List<Question> addQuestions(List<Question> questions);
}
