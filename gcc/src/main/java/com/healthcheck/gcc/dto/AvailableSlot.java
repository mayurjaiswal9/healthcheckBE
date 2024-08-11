package com.healthcheck.gcc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlot {
    private Long providerId;
    private LocalDate slotDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
