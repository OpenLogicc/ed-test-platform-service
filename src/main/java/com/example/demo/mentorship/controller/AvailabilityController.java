package com.example.demo.mentorship.controller;

// AvailabilityController.java

import com.example.demo.mentorship.dto.AvailableSlotsResponse;
import com.example.demo.mentorship.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AvailabilityController {

    private final SlotService slotService;

    @GetMapping("/{mentorId}/slots")
    public ResponseEntity<AvailableSlotsResponse> getAvailableSlots(
            @PathVariable Long mentorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(new AvailableSlotsResponse(slotService.getAvailableSlots(mentorId, date)));
    }
}
