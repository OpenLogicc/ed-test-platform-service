package com.example.demo.mentorship.service;

// SlotService.java

import com.example.demo.mentorship.entity.Booking;
import com.example.demo.mentorship.entity.MentorAvailability;
import com.example.demo.mentorship.repository.BookingRepository;
import com.example.demo.mentorship.repository.MentorAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final MentorAvailabilityRepository availabilityRepo;
    private final BookingRepository bookingRepo;
    private final DateTimeFormatter slotFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

    public List<String> getAvailableSlots(Long mentorId, LocalDate date) {
        // Derive DayOfWeek automatically from the user-selected date
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<MentorAvailability> schedules = availabilityRepo.findByMentorIdAndDayOfWeek(mentorId, dayOfWeek);
        if (schedules.isEmpty()) {
            return Collections.emptyList();
        }

        Set<LocalTime> bookedStartTimes = bookingRepo
                .findByMentorIdAndBookingDateAndStatus(mentorId, date, "CONFIRMED")
                .stream()
                .map(Booking::getStartTime)
                .collect(Collectors.toSet());

        List<String> availableSlots = new ArrayList<>();

        for (MentorAvailability schedule : schedules) {
            LocalTime current = schedule.getStartTime();
            int intervalMinutes = schedule.getSlotDurationMinutes();

            while (!current.plusMinutes(intervalMinutes).isAfter(schedule.getEndTime())) {
                if (!bookedStartTimes.contains(current)) {
                    availableSlots.add(current.format(slotFormatter));
                }
                current = current.plusMinutes(intervalMinutes);
            }
        }

        return availableSlots;
    }
}
