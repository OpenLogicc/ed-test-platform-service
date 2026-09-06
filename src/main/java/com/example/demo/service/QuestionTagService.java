package com.example.demo.service;

import com.example.demo.dto.WordTagScoreDto;
import com.example.demo.entity.WordTagScore;
import com.example.demo.mapper.WordTagScoreMapper;
import com.example.demo.model.MemorySpace;
import com.example.demo.repository.WordTagScoreRepository;
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

    private final WordTagScoreRepository wordTagScoreRepository;

    private final WordTagScoreMapper wordTagScoreMapper;

    public QuestionTagService(Cache<String, WordTagScoreDto> wordTagCache, ObjectMapper objectMapper,
                              WordTagScoreRepository wordTagScoreRepository, WordTagScoreMapper wordTagScoreMapper) {
        this.wordTagCache = wordTagCache;
        this.objectMapper = objectMapper;
        this.wordTagScoreRepository = wordTagScoreRepository;
        this.wordTagScoreMapper = wordTagScoreMapper;
    }

    public List<WordTagScoreDto> loadTags(String fileName) throws IOException {

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

    public int loadTags(String fileName, MemorySpace memorySpace) {
        int loadedTags = 0;
        ClassPathResource resource =
                new ClassPathResource("tags/" + fileName);

        List<WordTagScoreDto> wordTagScoreDtos;

        try (InputStream inputStream = resource.getInputStream()) {

            wordTagScoreDtos = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<WordTagScoreDto>>() {}
            );
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        switch (memorySpace) {
            case MemorySpace.RELATIONAL_DB -> loadedTags = loadTagsIntoDB(wordTagScoreDtos);
            case MemorySpace.CAFFEINE_CACHE -> loadedTags = loadTagsIntoCache(wordTagScoreDtos);
        }
        return loadedTags;
    }

    public int loadTagsIntoDB(List<WordTagScoreDto> wordTagScoreDtos) {
        List<WordTagScore> wordTags = wordTagScoreDtos
                .stream()
                .map(wordTagScoreMapper::toEntity)
                .toList();
        wordTagScoreRepository.saveAll(wordTags);
        return wordTags.size();
    }

    public int loadTagsIntoCache(List<WordTagScoreDto> wordTags) {
        int loadedRecords = 0;
        List<WordTagScoreDto> limitedWordTags = wordTags
                .stream()
                .limit(wordTagCache.estimatedSize())
                .toList();
        limitedWordTags.forEach(wordTag -> {
            wordTagCache.put(wordTag.word(), wordTag);
        });
        return limitedWordTags.size();
    }

    // TODO: implement a method to fetch the word tags from database
    public List<WordTagScoreDto> getWordTagsFromDB (List<String> words) {
        List<WordTagScore> wordTagScores = wordTagScoreRepository.findAllByWords(words);
        return wordTagScores
                .stream()
                .map(wordTagScoreMapper::toDto)
                .toList();
    }
}
