package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID testId;

    @OneToMany
    private List<Question> questions;


    private Integer testDuration; // in seconds

    private String testName;

    private String testType;

    private List<String> visibleTo;

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
