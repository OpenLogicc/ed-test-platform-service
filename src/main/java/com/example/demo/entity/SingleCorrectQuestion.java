package com.example.demo.entity;

import com.example.demo.model.Subject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SingleCorrectQuestion extends Question{

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> options;

    public SingleCorrectQuestion() {
    }

    public SingleCorrectQuestion(Subject subject, String questionType) {
        super(subject, questionType);
    }

    public List<Answer> getOptions() {
        return options;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }
}
