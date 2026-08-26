package com.example.demo.mapper;

import com.example.demo.dto.QuestionDto;
import com.example.demo.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends QuestionTypeProvider{

    QuestionDto toDto(Question question);

    Question toQuestion(QuestionDto questionDto);

    @Override
    default String questionType() {
        return "Base";
    };
}
