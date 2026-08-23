package com.example.demo.service;

import com.example.demo.dto.QuestionDto;
import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepositpry;

    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepositpry, QuestionMapper questionMapper) {
        this.questionRepositpry = questionRepositpry;
        this.questionMapper = questionMapper;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepositpry.findAll();
    }

    @Override
    public List<Question> addQuestions(List<Question> questions) {
        return questionRepositpry.saveAll(questions);
    }

    public int saveQuestions(List<QuestionDto> questionDtos) {
        List<Question> questions = questionDtos
                .stream()
                .map(this::convertDtoToQuestion)
                .toList();

        questionRepositpry.saveAll(questions);

        return questions.size();
    }

    private Question convertDtoToQuestion(QuestionDto dto) {
        Question question = questionMapper.toQuestion(dto);
        question.setCreatedOn(LocalDate.now());
        if (CollectionUtil.isNullOrEmpty(question.getTags())) {
            question.setTags(determineQuestionTags(question.getQuestionDescription()));
        }
        return question;
    }

    // TODO: implement this using a probability based scoring system based on keyword identification
    private final List<String> determineQuestionTags (String questionDescription) {
        return Collections.emptyList();
    }
}
