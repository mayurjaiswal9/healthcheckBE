package com.healthcheck.gcc.service;

import com.healthcheck.gcc.persistance.entity.Reminder;
import com.healthcheck.gcc.persistance.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public Optional<Reminder> getReminderById(Long id) {
        return reminderRepository.findById(id);
    }

    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public Reminder updateReminder(Long id, Reminder reminderDetails) {
        Reminder reminder = reminderRepository.findById(id).orElseThrow(() -> new RuntimeException("Reminder not found with id " + id));
        reminder.setUserId(reminderDetails.getUserId());
        reminder.setMessage(reminderDetails.getMessage());
        reminder.setReminderTime(reminderDetails.getReminderTime());
        reminder.setStatus(reminderDetails.getStatus());
        reminder.setRecurrenceType(reminderDetails.getRecurrenceType());
        reminder.setRecurrenceInterval(reminderDetails.getRecurrenceInterval());
        reminder.setRecurrenceStartDate(reminderDetails.getRecurrenceStartDate());
        reminder.setDailyTimes(reminderDetails.getDailyTimes());
        reminder.setDayOfMonth(reminderDetails.getDayOfMonth());
        reminder.setWeekOfMonth(reminderDetails.getWeekOfMonth());
        reminder.setDayOfWeek(reminderDetails.getDayOfWeek());
        return reminderRepository.save(reminder);
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }
}
