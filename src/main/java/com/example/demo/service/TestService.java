package com.example.demo.service;

import com.example.demo.entity.Test;
import com.example.demo.model.TestPreparationParameters;

public interface TestService {

    public Test createTest(TestPreparationParameters testPreparationParameters);
}
