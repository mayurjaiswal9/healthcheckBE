package com.healthcheck.gcc.controller;

import com.healthcheck.gcc.persistance.entity.Reminder;
import com.healthcheck.gcc.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable("id") Long id) {
        return reminderService.getReminderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderService.createReminder(reminder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable("id") Long id, @RequestBody Reminder reminderDetails) {
        try {
            Reminder updatedReminder = reminderService.updateReminder(id, reminderDetails);
            return ResponseEntity.ok(updatedReminder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable("id") Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}
