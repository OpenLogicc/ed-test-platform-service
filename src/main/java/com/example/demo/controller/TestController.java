package com.example.demo.controller;

import com.example.demo.entity.Test;
import com.example.demo.model.TestPreparationParameters;
import com.example.demo.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/")
    public Test createTest (@RequestBody TestPreparationParameters parameters) {
        return testService.createTest(parameters);
    }
}
