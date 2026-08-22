package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID answerId;

    private String answerDescription;

    public Answer(String answerDescription) {
        this.answerDescription = answerDescription;
    }

    public Answer() {

    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }
}
