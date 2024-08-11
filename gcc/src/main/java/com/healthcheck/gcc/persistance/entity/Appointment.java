package com.healthcheck.gcc.persistance.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;  // Name of the service (e.g., consultation, meeting)
    private String providerName; // Name of the person providing the service
    private String clientName;   // Name of the person receiving the service
    private String location;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_slot_id", nullable = true)
    @JsonManagedReference
    private AppointmentSlot appointmentSlot; // Date and time of the appointment
    private String status; // Status of the appointment (e.g., upcoming, complete, cancel)
    private String notes;
}
