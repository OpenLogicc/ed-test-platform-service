package com.example.demo.service;

import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.SingleCorrectQuestionDto;
import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.SingleCorrectQuestionMapper;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepositpry;

    private final SingleCorrectQuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepositpry, SingleCorrectQuestionMapper questionMapper) {
        this.questionRepositpry = questionRepositpry;
        this.questionMapper = questionMapper;
    }

    @Override
    public List<SingleCorrectQuestion> getAllQuestions() {
        return questionRepositpry.findAllSingleCorrectQuestions();
    }

    @Override
    public List<Question> addQuestions(List<Question> questions) {
        return questionRepositpry.saveAll(questions);
    }

    @Override
    public int saveSingleCorrectQuestions(List<SingleCorrectQuestionDto> questionDtos) {
        List<Question> questions = questionDtos
                .stream()
                .map(this::convertDtoToQuestion)
                .toList();

        questionRepositpry.saveAll(questions);

        return questions.size();
    }

    private Question convertDtoToQuestion(SingleCorrectQuestionDto dto) {
        SingleCorrectQuestion question = questionMapper.toEntity(dto);
        question.setQuestionDifficulty(dto.getQuestionDifficulty());
        question.setCreatedOn(LocalDate.now());
        if (CollectionUtil.isNullOrEmpty(question.getTags())) {
            question.setTags(determineQuestionTags(question.getQuestionDescription()));
        }
        if (!CollectionUtil.isNullOrEmpty(question.getOptions())) {
            question.getOptions().forEach(answer -> {
                answer.setQuestion(question);
            });
        }
        return question;
    }

    // TODO: implement this using a probability based scoring system based on keyword identification
    private final List<String> determineQuestionTags (String questionDescription) {\

        return Collections.emptyList();
    }
}
