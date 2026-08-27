package com.example.demo.entity;

import com.example.demo.model.QuestionDifficulty;
import com.example.demo.model.Subject;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "question_bank",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"question_id", "version"}
        )
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionId;

    @Column(name = "question_description", length = 1000, nullable = false)
    private String questionDescription;

    @Enumerated(value = EnumType.STRING)
    private QuestionDifficulty questionDifficulty;

    @ElementCollection
    private List<String> tags;

    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    @Column(name = "question_type")
    private String questionType;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "created_on")
    private LocalDate createdOn;

    public Question() {
    }

    public Question (Subject subject, String questionType) {
        this.subject = subject;
        this.questionType = questionType;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public QuestionDifficulty getDifficulty() {
        return questionDifficulty;
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

    public void setQuestionDifficulty(QuestionDifficulty difficulty) {
        this.questionDifficulty = difficulty;
    }

    public QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getQuestionType() {
        return questionType;
    }

    public Test getTest() {
        return test;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
