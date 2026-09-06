package com.example.demo.mentorship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class BookingRequest {

    @NotNull(message = "Mentor ID is required")
    private Long mentorId;

    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    // Explicitly enforce 12-hour format with AM/PM and US Locale
    @NotNull(message = "Start time is required")
    @JsonFormat(pattern = "hh:mm a", locale = "en_US")
    private LocalTime startTime;

    @NotNull(message = "Session duration is required")
    private Integer durationMinutes;

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotBlank(message = "Topic is required")
    private String topic;

    private String questions;

    @NotBlank(message = "Preferred language is required")
    private String preferredLanguage;

    @NotBlank(message = "Mentee email is required")
    @Email(message = "Invalid email format")
    private String menteeEmail;

    @NotBlank(message = "Mentee phone number is required")
    private String menteePhone;

    private String additionalNotes;
}