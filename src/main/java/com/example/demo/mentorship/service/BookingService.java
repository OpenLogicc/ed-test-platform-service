package com.example.demo.mentorship.service;

import com.example.demo.mentorship.dto.BookingRequest;
import com.example.demo.mentorship.dto.BookingResponse;
import com.example.demo.mentorship.entity.Booking;
import com.example.demo.mentorship.exception.SlotConflictException;
import com.example.demo.mentorship.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;

    // Formatter to parse "10:00 AM", "02:30 PM", etc.
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

    public BookingResponse createBooking(BookingRequest request) {
        // Start time is already a LocalTime (deserialized by Jackson); use it directly
        LocalTime parsedStartTime = request.getStartTime();

        boolean isAlreadyBooked = bookingRepo.existsByMentorIdAndBookingDateAndStartTimeAndStatus(
                request.getMentorId(),
                request.getBookingDate(),
                parsedStartTime,
                "CONFIRMED"
        );

        if (isAlreadyBooked) {
            throw new SlotConflictException("The selected time slot is already booked.");
        }

        Booking booking = Booking.builder()
                .mentorId(request.getMentorId())
                .bookingDate(request.getBookingDate())
                .startTime(parsedStartTime)
                .durationMinutes(request.getDurationMinutes())
                .platform(request.getPlatform())
                .topic(request.getTopic())
                .questions(request.getQuestions())
                .preferredLanguage(request.getPreferredLanguage())
                .menteeEmail(request.getMenteeEmail())
                .menteePhone(request.getMenteePhone())
                .additionalNotes(request.getAdditionalNotes())
                .status("CONFIRMED")
                .build();

        try {
            Booking saved = bookingRepo.save(booking);
            return BookingResponse.builder()
                    .bookingId(saved.getId())
                    .mentorId(saved.getMentorId())
                    .bookingDate(saved.getBookingDate())
                    .startTime(saved.getStartTime())
                    .durationMinutes(saved.getDurationMinutes())
                    .platform(saved.getPlatform())
                    .topic(saved.getTopic())
                    .status(saved.getStatus())
                    .message("Session successfully scheduled")
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new SlotConflictException("The selected time slot is already booked.");
        }
    }
}