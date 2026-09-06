package com.example.demo.mapper;

import com.example.demo.dto.WordTagScoreDto;
import com.example.demo.entity.WordTagScore;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordTagScoreMapper {

    WordTagScore toEntity(WordTagScoreDto dto);

    WordTagScoreDto toDto(WordTagScore entity);
}
