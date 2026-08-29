package com.example.demo.service;

import com.example.demo.dto.WordTagScoreDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionTagService {

    private final Cache<String, WordTagScoreDto> wordTagCache;

    private final ObjectMapper objectMapper;

    public QuestionTagService(Cache<String, WordTagScoreDto> wordTagCache, ObjectMapper objectMapper) {
        this.wordTagCache = wordTagCache;
        this.objectMapper = objectMapper;
    }

    public List<WordTagScoreDto> loadTagsIntoCache(String fileName) throws IOException {

        ClassPathResource resource =
                new ClassPathResource("tags/" + fileName);

        try (InputStream inputStream = resource.getInputStream()) {

            List<WordTagScoreDto> wordTags = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<WordTagScoreDto>>() {}
            );

            wordTags.forEach(wordTag -> {
                wordTagCache.put(wordTag.word(), wordTag);
            });

            return wordTags;
        }
    }

    // TODO: implement a method to fetch the word tags from database
    public List<WordTagScoreDto> getWordTagsFromDB (List<String> words) {
        return Collections.emptyList();
    }
}
