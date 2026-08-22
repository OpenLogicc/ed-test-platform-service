package com.example.demo.model;

public enum TestDifficulty {
    EASY(0.5f, 0.3f, 0.2f),
    MEDIUM(0.3f, 0.5f, 0.2f),
    HARD(0.2f, 0.3f, 0.5f);

    private final float easyPercentage;
    private final float mediumPercentage;
    private final float hardPercentage;

    private TestDifficulty(float easyPercentage, float mediumPercentage, float hardPercentage) {
        this.easyPercentage = easyPercentage;
        this.mediumPercentage = mediumPercentage;
        this.hardPercentage = hardPercentage;
    }

    public float getEasyPercentage() {
        return easyPercentage;
    }

    public float getMediumPercentage() {
        return mediumPercentage;
    }

    public float getHardPercentage() {
        return hardPercentage;
    }
}
