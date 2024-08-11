package com.healthcheck.gcc.persistance.repository;

import com.healthcheck.gcc.persistance.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a JOIN a.appointmentSlot s WHERE s.userId = :userId")
    List<Appointment> findByUserId(@Param("userId") Long userId);
}
