package com.example.demo.mentorship.entity;

// Booking.java

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_mentor_slot", columnNames = {"mentorId", "bookingDate", "startTime", "status"})
        }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mentorId;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String topic;

    @Column(columnDefinition = "TEXT")
    private String questions;

    @Column(nullable = false)
    private String preferredLanguage;

    @Column(nullable = false)
    private String menteeEmail;

    @Column(nullable = false)
    private String menteePhone;

    @Column(columnDefinition = "TEXT")
    private String additionalNotes;

    @Column(nullable = false)
    private String status; // "CONFIRMED", "CANCELLED"
}
