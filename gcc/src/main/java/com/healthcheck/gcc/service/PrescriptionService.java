package com.healthcheck.gcc.service;

import com.healthcheck.gcc.persistance.entity.Prescription;
import com.healthcheck.gcc.persistance.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public List<Prescription> getPrescriptionsByUserId(Long userId) {
        return prescriptionRepository.findByUserId(userId);
    }

    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
        return prescriptionRepository.findById(id).map(prescription -> {
            prescription.setName(prescriptionDetails.getName());
            prescription.setDose(prescriptionDetails.getDose());
            prescription.setForm(prescriptionDetails.getForm());
            prescription.setFrequencies(prescriptionDetails.getFrequencies());
            prescription.setDurationDays(prescriptionDetails.getDurationDays());
            prescription.setPrescribedBy(prescriptionDetails.getPrescribedBy());
            prescription.setPrescriptionDate(prescriptionDetails.getPrescriptionDate());
            prescription.setNotes(prescriptionDetails.getNotes());
            prescription.setStatus(prescriptionDetails.getStatus());
            return prescriptionRepository.save(prescription);
        }).orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
