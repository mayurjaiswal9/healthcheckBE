package com.healthcheck.gcc.persistance.repository;

import com.healthcheck.gcc.persistance.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
