package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    QuestionRepository questionRepositpry;

    public QuestionServiceImpl(QuestionRepository questionRepositpry) {
        this.questionRepositpry = questionRepositpry;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepositpry.findAll();
    }

    public List<Question> addQuestions(List<Question> questions) {
        return questionRepositpry.saveAll(questions);
    }
}
