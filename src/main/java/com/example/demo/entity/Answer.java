package com.example.demo.entity;

import com.example.demo.model.BinaryClassifier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID answerId;

    private String answerDescription;

    private BinaryClassifier isCorrect;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_id")
    private SingleCorrectQuestion question;

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

    public BinaryClassifier getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(BinaryClassifier isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setAnswerId(UUID answerId) {
        this.answerId = answerId;
    }

    public SingleCorrectQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SingleCorrectQuestion question) {
        this.question = question;
    }
}
