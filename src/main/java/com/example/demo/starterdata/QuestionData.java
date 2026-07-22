package com.example.demo.starterdata;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
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

        SingleCorrectQuestion question = new SingleCorrectQuestion("Chemistry", "SCQ");
        question.setQuestionDescription("Which of the following organic compound is most acidic");
        question.setDifficulty("Easy");
        question.setTags(List.of("organic chemistry", "goc"));
        question.setOptions(options1);
        question.setCorrectOption(correctAnswerIndex1);

        questionRepository.save(question);

    }


}
