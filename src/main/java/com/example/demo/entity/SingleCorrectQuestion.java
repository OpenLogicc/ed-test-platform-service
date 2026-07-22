package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SingleCorrectQuestion extends Question{

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> options;
    private int correctOption;

    public SingleCorrectQuestion(String subject, String questionType) {
        super(subject, questionType);
    }

    public List<Answer> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }
}
