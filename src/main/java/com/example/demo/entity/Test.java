package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
public class Test {

    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID testId;

    @OneToMany(mappedBy = "questionId")
    private List<Question> questions;

    @Column(name = "test_duration")
    private Integer testDuration; // in seconds

    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_type")
    private String testType;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "test_id"))
    @Column(name = "group_id")
    private List<String> visibleTo;

    @Column(name = "created_on")
    private LocalDate createdOn;

    public UUID getTestId() {
        return testId;
    }

    public void setTestId(UUID testId) {
        this.testId = testId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(Integer testDuration) {
        this.testDuration = testDuration;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public List<String> getVisibleTo() {
        return visibleTo;
    }

    public void setVisibleTo(List<String> visibleTo) {
        this.visibleTo = visibleTo;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", questions=" + questions +
                ", testDuration=" + testDuration +
                ", testName='" + testName + '\'' +
                ", testType='" + testType + '\'' +
                ", visibleTo=" + visibleTo +
                '}';
    }
}
