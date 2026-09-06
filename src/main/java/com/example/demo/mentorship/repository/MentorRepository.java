package com.example.demo.mentorship.repository;

// MentorRepository.java
import com.example.demo.mentorship.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
}