package com.healthcheck.gcc.persistance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long providerId; // ID of the person providing the service
    private Long userId; // ID of the person receiving the service
    private LocalDateTime startTime; // Start date and time of the slot
    private LocalDateTime endTime; // End date and time of the slot
    private String status; // Status of the slot (e.g., confirmed, pending, available)

    @OneToOne(mappedBy = "appointmentSlot", cascade = CascadeType.ALL)
    @JsonBackReference
    private Appointment appointment;
}
