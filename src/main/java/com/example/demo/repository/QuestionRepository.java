package com.example.demo.repository;

import com.example.demo.entity.Question;
import com.example.demo.entity.SingleCorrectQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM SingleCorrectQuestion q")
    List<SingleCorrectQuestion> findAllSingleCorrectQuestions();
}
