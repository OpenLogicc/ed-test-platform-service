package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "tag_details")
@Entity
public class WordTagScore {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID wordTagId;

    @Column(name = "word")
    private String word;

    @Column(name = "associated_tag")
    private String tag;

    @Column(name = "indicator_score")
    private Integer Score;

    public UUID getWordTagId() {
        return wordTagId;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
