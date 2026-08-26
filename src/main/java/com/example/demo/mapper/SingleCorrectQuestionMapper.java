package com.example.demo.mapper;

import com.example.demo.dto.SingleCorrectQuestionDto;
import com.example.demo.entity.SingleCorrectQuestion;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = "spring")
public interface SingleCorrectQuestionMapper extends QuestionTypeProvider{

    SingleCorrectQuestionDto toDto(SingleCorrectQuestion scq);

    SingleCorrectQuestion toEntity(SingleCorrectQuestionDto dto);

    @Override
    default String questionType() {
        return "SCQ";
    }
}
