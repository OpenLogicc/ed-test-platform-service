package com.example.demo.dto;

import com.example.demo.entity.Answer;

import java.util.List;
import java.util.UUID;

public class SingleCorrectQuestionDto extends QuestionDto{
    private List<Answer> options;

    private UUID correctAnswer;

    public SingleCorrectQuestionDto() {
    }

    public List<Answer> getOptions() {
        return options;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }

    public UUID getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(UUID correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Answer correctAnswer() {
        return options.stream()
                .filter(answer -> answer.getAnswerId() == correctAnswer)
                .findFirst()
                .get();
    }
}
