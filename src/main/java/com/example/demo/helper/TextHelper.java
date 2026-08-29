package com.example.demo.helper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class TextHelper {

    public static List<String> extractPhrases(String text, int minGroupSize, int maxGroupSize) {
        List<String> wordsInSequence = extractNormalizedWords(text);
        List<List<String>> sizedPhrases = IntStream.rangeClosed(minGroupSize,maxGroupSize)
                .mapToObj(a -> groupWordsIntoPhrases(wordsInSequence, a))
                .toList();
        return sizedPhrases
                .stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public static List<String> extractNormalizedWords(String text) {
        List<String> ans = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<text.length(); i++) {
            if(isCharacterAnAlphabet(text.charAt(i))) {
                s.append(text.charAt(i));
            }
            else if(!s.isEmpty()) {
                ans.add(s.toString());
                s.delete(0, s.length());
            }
        }
        if(!s.isEmpty()) {
            ans.add(s.toString());
        }
        return ans;
    }

    public static boolean isCharacterAnAlphabet(char a) {
        return (a>='a' && a<='z') || (a>='A' && a<='Z');
    }

    public static List<String> groupWordsIntoPhrases(List<String> words, int groupSize) {
        List<String> phrasesOfGroupSize = new ArrayList<>();
        for(int i = 0; i<words.size() - groupSize + 1; i++) {
            StringBuilder s = new StringBuilder(words.get(i));
            for(int j = i+1; j<i+groupSize; j++) {
                s.append(" ");
                s.append(words.get(j));
            }
            phrasesOfGroupSize.add(s.toString());
        }
        return phrasesOfGroupSize;
    }
}
