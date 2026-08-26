package com.example.demo.service;

import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.SingleCorrectQuestionDto;
import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;


import java.util.List;

public interface QuestionService {
    public List<SingleCorrectQuestion> getAllQuestions();

    public List<Question> addQuestions(List<Question> questions);

    public int saveSingleCorrectQuestions(List<SingleCorrectQuestionDto> questionDtos);
}
