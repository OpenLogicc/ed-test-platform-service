package com.example.demo.mentorship.repository;

// MentorAvailabilityRepository.java

import com.example.demo.mentorship.entity.MentorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.DayOfWeek;
import java.util.List;

public interface MentorAvailabilityRepository extends JpaRepository<MentorAvailability, Long> {
    List<MentorAvailability> findByMentorIdAndDayOfWeek(Long mentorId, DayOfWeek dayOfWeek);
}
