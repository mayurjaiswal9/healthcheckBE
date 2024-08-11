package com.healthcheck.gcc.controller;

import com.healthcheck.gcc.persistance.entity.Prescription;
import com.healthcheck.gcc.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionsController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("id") Long id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Prescription> getPrescriptionsByUserId(@PathVariable Long userId) {
        return prescriptionService.getPrescriptionsByUserId(userId);
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.createPrescription(prescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable("id") Long id, @RequestBody Prescription prescriptionDetails) {
        try {
            Prescription updatedPrescription = prescriptionService.updatePrescription(id, prescriptionDetails);
            return ResponseEntity.ok(updatedPrescription);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable("id") Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
