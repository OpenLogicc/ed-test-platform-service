package com.example.demo.service;

import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.SingleCorrectQuestionDto;
import com.example.demo.entity.Question;


import java.util.List;

public interface QuestionService {
    public List<Question> getAllQuestions();

    public List<Question> addQuestions(List<Question> questions);

    public int saveSingleCorrectQuestions(List<SingleCorrectQuestionDto> questionDtos);
}
