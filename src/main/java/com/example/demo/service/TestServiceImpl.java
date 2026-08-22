package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.entity.Test;
import com.example.demo.model.TestDifficulty;
import com.example.demo.model.TestPreparationParameters;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class TestServiceImpl implements TestService {

    public Test createTest(TestPreparationParameters parameters) {
        // Implement the logic to create a test based on the provided parameters
        Test test = new Test();
        test.setTestDuration(parameters.getTestDuration());
        test.setTestName(parameters.getTestName());
        int numberOfQuestions = parameters.getNumberOfQuestions();
        List<String> tagList = parameters.getTags();
        List<String> difficultyList = generateDifficultyList(numberOfQuestions, parameters.getTestDifficulty());
        List<String> questionTopics = new ArrayList<>();
        for (int i = 0; i<numberOfQuestions; i++) {
           int randomIndex = (int) (Math.random() * tagList.size());
           String randomTopic = tagList.get(randomIndex);
           questionTopics.add(randomTopic);
        }
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            Question question = new Question(questionTopics.get(i), "Single Correct");
            List <String> tags = new ArrayList<>();
            tags.add(questionTopics.get(i));
            question.setTags(tags);
            question.setDifficulty(difficultyList.get(i));
            questions.add(question);
        }
        test.setQuestions(questions);
        return test;
    }

    public List<String> generateDifficultyList(int numberOfQuestions, TestDifficulty testDifficulty) {
        List<String> questionDifficulties = List.of("Easy", "Medium", "Hard");
        List<String> selectedDifficulties = new ArrayList<>();
        System.out.println(testDifficulty.getEasyPercentage()*numberOfQuestions);
        int easyCount = (int) (testDifficulty.getEasyPercentage()*numberOfQuestions);
        int mediumCount = (int) (testDifficulty.getMediumPercentage()*numberOfQuestions);
        int hardCount = (int) (testDifficulty.getHardPercentage()*numberOfQuestions);
        System.out.println(easyCount + " " + mediumCount + " " + hardCount);
        if(easyCount + mediumCount + hardCount < numberOfQuestions) {
            easyCount += numberOfQuestions - (easyCount + mediumCount + hardCount);
        }
        for(int i=0; i<easyCount; i++) {
            selectedDifficulties.add(questionDifficulties.get(0));
        }
        for(int i=0; i<mediumCount; i++) {
            selectedDifficulties.add(questionDifficulties.get(1));
        }
        for(int i=0; i<hardCount; i++) {
            selectedDifficulties.add(questionDifficulties.get(2));
        }
        Collections.shuffle(selectedDifficulties);
        return selectedDifficulties;
    }
}
