package com.example.demo.mentorship.dto;

// BookingResponse.java

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class BookingResponse {
    private Long bookingId;
    private Long mentorId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private Integer durationMinutes;
    private String platform;
    private String topic;
    private String status;
    private String message;
}
