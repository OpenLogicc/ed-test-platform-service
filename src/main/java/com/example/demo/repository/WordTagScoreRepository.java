package com.example.demo.repository;

import com.example.demo.entity.WordTagScore;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WordTagScoreRepository extends JpaRepository<WordTagScore, UUID> {

    @Query(value = "Select * from tag_details where word in :words", nativeQuery = true)
    List<WordTagScore> findAllByWords(@Param("words") List<String> words);
}
