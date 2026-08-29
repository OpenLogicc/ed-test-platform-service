package com.example.demo.service;


import com.example.demo.dto.WordTagScoreDto;
import com.example.demo.helper.TextHelper;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TextClassifierService {
    private final QuestionTagService questionTagService;

    private final Cache<String, WordTagScoreDto> wordTagCache;

    private final int minPhraseLength = 1;

    private final int maxPhraseLength = 4;

    public TextClassifierService(QuestionTagService questionTagService, Cache<String, WordTagScoreDto> wordTagCache) {
        this.questionTagService = questionTagService;
        this.wordTagCache = wordTagCache;
    }

    public List<String> classifyText(String text) {
        List<String> phrases = TextHelper.extractPhrases(text, minPhraseLength, maxPhraseLength);
        List<WordTagScoreDto> wordTags = getAllWordTags(phrases);
        Map<String, Integer> tagScores = calculateScoreForEachTag(wordTags);
        List<String> identifiedTags = topTagsBasedOnScoreSum(tagScores, 5);
        identifiedTags.forEach(tag -> {
            System.out.println(tag + " " + tagScores.get(tag));
        });
        return identifiedTags;
    }

    private List<WordTagScoreDto> getAllWordTags(List<String> words) {
        List<WordTagScoreDto> cachedWordTags = words
                .stream()
                .distinct()
                .map(wordTagCache::getIfPresent)
                .filter(Objects::nonNull)
                .toList();

        Set<String> cachedWords = cachedWordTags
                .stream()
                .map(WordTagScoreDto::word)
                .collect(Collectors.toSet());

        List<String> wordsToQuery = words.stream()
                .distinct()
                .filter(word -> !cachedWords.contains(word))
                .toList();

        List<WordTagScoreDto> wordTagsFromDB = questionTagService.getWordTagsFromDB(wordsToQuery);

        return Stream.concat(cachedWordTags.stream(), wordTagsFromDB.stream()).toList();
    }

    private Map<String, Integer> calculateScoreForEachTag (List<WordTagScoreDto> wordTags) {
        return wordTags
                .stream()
                .collect(Collectors
                        .groupingBy(WordTagScoreDto::tag, Collectors
                                .summingInt(WordTagScoreDto::score)));
    }

    private List<String> topTagsBasedOnScoreSum(Map<String,Integer> tagScores, int k) {
        return tagScores
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }
}
