package com.example.demo.service;

import com.example.demo.dto.SingleCorrectQuestionDto;
import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;
import com.example.demo.mapper.SingleCorrectQuestionMapper;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    private final SingleCorrectQuestionMapper questionMapper;

    private final TextClassifierService textClassifierService;

    public QuestionServiceImpl(QuestionRepository questionRepository, SingleCorrectQuestionMapper questionMapper, TextClassifierService textClassifierService) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.textClassifierService = textClassifierService;
    }

    @Override
    public List<SingleCorrectQuestion> getAllQuestions() {
        return questionRepository.findAllSingleCorrectQuestions();
    }

    @Override
    public List<Question> addQuestions(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    @Override
    public int saveSingleCorrectQuestions(List<SingleCorrectQuestionDto> questionDtos) {
        List<Question> questions = questionDtos
                .stream()
                .map(this::convertDtoToQuestion)
                .toList();

        questionRepository.saveAll(questions);

        return questions.size();
    }

    private Question convertDtoToQuestion(SingleCorrectQuestionDto dto) {
        SingleCorrectQuestion question = questionMapper.toEntity(dto);
        question.setQuestionDifficulty(dto.getQuestionDifficulty());
        question.setCreatedOn(LocalDate.now());
        question.setTags(determineQuestionTags(question.getQuestionDescription()));
        if (!CollectionUtil.isNullOrEmpty(question.getOptions())) {
            question.getOptions().forEach(answer -> {
                answer.setQuestion(question);
            });
        }
        return question;
    }

    // TODO: implement this using a probability based scoring system based on keyword identification
    private List<String> determineQuestionTags (String questionDescription) {
        return textClassifierService.classifyText(questionDescription);
    }
}
