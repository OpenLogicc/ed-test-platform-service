package com.example.demo.factory;

import com.example.demo.mapper.QuestionTypeProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class QuestionMapperFactory {
    private Map<String, QuestionTypeProvider> registry;

//    public QuestionMapperFactory(List<QuestionTypeProvider> questionTypeProviders) {
//        this.registry = questionTypeProviders
//                .stream()
//                .collect(Collectors
//                        .toMap(QuestionTypeProvider::questionType, Function.identity(),
//                                (newKey, oldKey) -> newKey));
//    }

    public QuestionTypeProvider getQuestionMapper(String questionType) {
        if(!registry.containsKey(questionType)) {
            throw new RuntimeException("No implementations for this questionType " + questionType);
        }
        return registry.get(questionType);
    }

}
