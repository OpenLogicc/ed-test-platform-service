package com.example.demo.controller;

import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.SingleCorrectQuestionDto;
import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;
import com.example.demo.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/Question")
@RestController
public class QuestionController {


    private static final Logger LOGGER =
            LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<SingleCorrectQuestion> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping ("/add")
    public List<Question> addQuestions(List<Question> questions){
        return questionService.addQuestions(questions);
    }

    @PostMapping("/question-bank/add")
    public String saveSCQQuestions(@RequestBody List<SingleCorrectQuestionDto> questionDtos) {
        LOGGER.info("Request to persist {} questions received", questionDtos.size());
        int questionsSaved = questionService.saveSingleCorrectQuestions(questionDtos);
        return questionsSaved + " Questions Saved successfully in Question Bank";
    }
}
