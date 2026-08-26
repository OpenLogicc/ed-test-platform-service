package com.example.demo.entity;

import com.example.demo.model.Subject;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SingleCorrectQuestion extends Question{

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> options;
    private String correctOption;

    public SingleCorrectQuestion() {
    }

    public SingleCorrectQuestion(Subject subject, String questionType) {
        super(subject, questionType);
    }

    public List<Answer> getOptions() {
        return options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
}
