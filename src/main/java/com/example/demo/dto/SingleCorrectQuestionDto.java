package com.example.demo.dto;

import com.example.demo.entity.Answer;
import com.example.demo.model.BinaryClassifier;

import java.util.List;
import java.util.UUID;

public class SingleCorrectQuestionDto extends QuestionDto{
    private List<Answer> options;

    public SingleCorrectQuestionDto() {
    }

    public List<Answer> getOptions() {
        return options;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }

    public Answer correctAnswer() {
        return options.stream()
                .filter(answer -> answer.getIsCorrect() == BinaryClassifier.YES)
                .findFirst()
                .get();
    }
}
