package com.healthcheck.gcc.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String message;
    private LocalDateTime reminderTime;

    @Enumerated(EnumType.STRING)
    private ReminderStatus status;

    @Enumerated(EnumType.STRING)
    private RecurrenceType recurrenceType;

    private Integer recurrenceInterval; // Interval for recurrence (e.g., every 2 days/weeks/months)

    private LocalDateTime recurrenceStartDate; // Start date for the recurrence

    @ElementCollection
    private List<LocalDateTime> dailyTimes; // Times within the same day

    private Integer dayOfMonth; // Day of the month for monthly recurrences (e.g., 15th of every month)

    private Integer weekOfMonth; // Week of the month for weekly/monthly recurrences (e.g., 2nd week of the month)

    private Integer dayOfWeek; // Day of the week for weekly recurrences (e.g., Monday)
}
