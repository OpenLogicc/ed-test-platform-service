package com.example.demo.controller;

import com.example.demo.dto.WordTagScoreDto;
import com.example.demo.model.MemorySpace;
import com.example.demo.service.QuestionTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequestMapping("/tags")
@RestController
public class QuestionTagController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(QuestionTagController.class);

    private final QuestionTagService questionTagService;

    public QuestionTagController(QuestionTagService questionTagService) {
        this.questionTagService = questionTagService;
    }

    @PostMapping
    public int loadTags(String fileName, MemorySpace memorySpace) {
        try { return questionTagService.loadTags(fileName, memorySpace);}
        catch (Exception e) {
            LOGGER.info("Exception occurred while processing : ", e);
            return 0;
        }
    }
}
