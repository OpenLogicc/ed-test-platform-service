package com.example.demo.starterdata;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;
import com.example.demo.entity.Test;
import com.example.demo.model.QuestionDifficulty;
import com.example.demo.model.Subject;
import com.example.demo.model.TestDifficulty;
import com.example.demo.model.TestPreparationParameters;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.TestServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class QuestionData implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    public QuestionData(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }



    @Override
    public void run(String... args) throws Exception {

        Answer answer1 = new Answer("ethanol");
        Answer answer2 = new Answer("propanone");
        Answer answer3 = new Answer("ethanamine");
        Answer answer4 = new Answer("ethanal");

        List<Answer> options1 = List.of(answer1, answer2, answer3, answer4);
        int correctAnswerIndex1 = 0;

        SingleCorrectQuestion question = new SingleCorrectQuestion(Subject.CHEMISTRY, "SCQ");
        question.setQuestionDescription("Which of the following organic compound is most acidic");
        question.setDifficulty(QuestionDifficulty.EASY);
        question.setTags(List.of("organic chemistry", "goc"));
        question.setOptions(options1);
        question.setCorrectOption("A");
        question.setCreatedOn(LocalDate.now());

        questionRepository.save(question);

        TestServiceImpl testService = new TestServiceImpl();
        List<QuestionDifficulty> difficulties = testService.generateDifficultyList(15, TestDifficulty.EASY);
        difficulties.forEach(difficulty -> System.out.println("Difficulty: " + difficulty));

        TestPreparationParameters testPreparationParameters = new TestPreparationParameters();
        testPreparationParameters.setTestDifficulty(TestDifficulty.HARD);
        testPreparationParameters.setTestDuration(45);
        testPreparationParameters.setNumberOfQuestions(10);
        testPreparationParameters.setTestName("Gaand Faad test");
        List<String> topics = List.of("Quadratic Equation", "Circles", "Probability", "Mensuration", "Algebra", "Trigonometry");
        testPreparationParameters.setTags(topics);

        Test myTest = testService.createTest(testPreparationParameters);
        System.out.println(myTest);

        myTest.getQuestions().forEach(
question1 -> {
            System.out.println("Difficulty: " + question1.getDifficulty());
            System.out.println("Tags: " + question1.getTags());
        }
        );

    }


}
