package com.example.demo.mentorship.repository;

// BookingRepository.java

import com.example.demo.mentorship.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByMentorIdAndBookingDateAndStatus(Long mentorId, LocalDate bookingDate, String status);
    boolean existsByMentorIdAndBookingDateAndStartTimeAndStatus(Long mentorId, LocalDate bookingDate, LocalTime startTime, String status);
}
