package com.healthcheck.gcc.persistance.repository;

import com.healthcheck.gcc.persistance.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT p FROM Prescription p WHERE p.userId = :userId")
    List<Prescription> findByUserId(@Param("userId") Long userId);
}
