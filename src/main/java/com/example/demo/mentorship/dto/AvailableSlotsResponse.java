package com.example.demo.mentorship.dto;

// AvailableSlotsResponse.java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotsResponse {
    private List<String> availableSlots;
}
