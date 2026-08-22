package com.example.demo.model;

import java.util.List;

public class TestPreparationParameters {

    private String TestName;

    private int numberOfQuestions;

    private List<String> tags;

    private TestDifficulty testDifficulty;

    private int testDuration; // in seconds

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public TestDifficulty getTestDifficulty() {
        return testDifficulty;
    }

    public void setTestDifficulty(TestDifficulty testDifficulty) {
        this.testDifficulty = testDifficulty;
    }

    public int getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(int testDuration) {
        this.testDuration = testDuration;
    }
}
