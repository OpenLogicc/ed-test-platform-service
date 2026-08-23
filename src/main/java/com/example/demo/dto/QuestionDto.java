package com.example.demo.dto;

import com.example.demo.model.QuestionDifficulty;
import com.example.demo.model.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDto {

    private String questionDescription;

    private QuestionDifficulty questionDifficulty;

    private List<String> tags;

    private Subject subject;

    private String questionType;

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(QuestionDifficulty questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
