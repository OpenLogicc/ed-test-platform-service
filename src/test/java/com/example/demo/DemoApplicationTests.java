package com.example.demo;

import com.example.demo.helper.TextHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testWordNormalizer() {
		String text = "What is the formula for magnetic field due to a ring at a " +
				"point on its axis at a distance r away from its center";
        List<String> words = TextHelper.extractNormalizedWords(text);
		words.forEach(System.out::println);
	}

	@Test
	void testPhraseMaker() {
		List<String> words = List.of("Memento", "Prestige", "Inception", "Interstellar", "Godfather");
		List<String> phrases = TextHelper.groupWordsIntoPhrases(words, 2);
		phrases.forEach(System.out::println);
	}

	@Test
	void testExtractPhrasesFromText() {
		String text = "What is the formula for magnetic field due to a ring at a " +
				"point on its axis at a distance r away from its center";
		List<String> phrases = TextHelper.extractPhrases(text, 1, 3);
		phrases.forEach(System.out::println);
	}

}
