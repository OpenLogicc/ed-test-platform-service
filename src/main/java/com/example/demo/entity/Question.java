package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"question_id", "version"}
        )
)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionId;

    private String questionDescription;

    private String difficulty;

    @ElementCollection
    private List<String> tags;

    private final String subject;

    private final String questionType;

    public Question (String subject, String questionType) {
        this.subject = subject;
        this.questionType = questionType;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<String> getTags() {
        return tags;
    }


    public UUID getQuestionId() {
        return questionId;
    }


    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSubject() {
        return subject;
    }

    public String getQuestionType() {
        return questionType;
    }
}
