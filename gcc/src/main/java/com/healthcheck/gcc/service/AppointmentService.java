package com.healthcheck.gcc.service;

import com.healthcheck.gcc.dto.AvailableSlot;
import com.healthcheck.gcc.persistance.entity.Appointment;
import com.healthcheck.gcc.persistance.entity.AppointmentSlot;
import com.healthcheck.gcc.persistance.entity.Provider;
import com.healthcheck.gcc.persistance.repository.AppointmentRepository;
import com.healthcheck.gcc.persistance.repository.AppointmentSlotRepository;
import com.healthcheck.gcc.persistance.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private AppointmentSlotRepository appointmentSlotRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getAppointmentSlot() != null) {
            appointment.getAppointmentSlot().setProviderId(appointment.getAppointmentSlot().getProviderId()); // Ensure providerId consistency
            appointment.getAppointmentSlot().setUserId(appointment.getAppointmentSlot().getUserId()); // Ensure userId consistency
            appointment.getAppointmentSlot().setAppointment(appointment); // Set the reference back to the appointment
        }
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setServiceName(appointmentDetails.getServiceName());
                    appointment.setProviderName(appointmentDetails.getProviderName());
                    appointment.setClientName(appointmentDetails.getClientName());
                    appointment.setLocation(appointmentDetails.getLocation());
                    appointment.setStatus(appointmentDetails.getStatus());
                    appointment.setNotes(appointmentDetails.getNotes());

                    AppointmentSlot appointmentSlotDetails = appointmentDetails.getAppointmentSlot();
                    if (appointmentSlotDetails != null) {
                        AppointmentSlot appointmentSlot = appointment.getAppointmentSlot();
                        if (appointmentSlot == null) {
                            appointmentSlot = new AppointmentSlot();
                        }
                        appointmentSlot.setProviderId(appointmentSlotDetails.getProviderId()); // Ensure providerId consistency
                        appointmentSlot.setUserId(appointmentSlotDetails.getUserId()); // Ensure userId consistency
                        appointmentSlot.setStartTime(appointmentSlotDetails.getStartTime());
                        appointmentSlot.setEndTime(appointmentSlotDetails.getEndTime());
                        appointmentSlot.setStatus(appointmentSlotDetails.getStatus());
                        appointmentSlot.setAppointment(appointment);
                        appointmentSlotRepository.save(appointmentSlot);
                        appointment.setAppointmentSlot(appointmentSlot);
                    } else {
                        appointment.setAppointmentSlot(null);
                    }

                    return appointmentRepository.save(appointment);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id).ifPresent(appointment -> {
            if (appointment.getAppointmentSlot() != null) {
                appointmentSlotRepository.delete(appointment.getAppointmentSlot());
            }
            appointmentRepository.deleteById(id);
        });
    }


    public List<Provider> getProvidersByType(Long typeId) {
        return providerRepository.findProvidersByType(typeId);
    }

    public List<AvailableSlot> getAvailableSlotsByProviderType(int slotDuration, Long typeId) {
        List<Provider> providers = getProvidersByType(typeId);
        List<Integer> providerIds = providers.stream()
                .map(provider -> provider.getProviderId().intValue())
                .collect(Collectors.toList());
        return getAvailableSlots(slotDuration, providerIds);
    }

    private List<AvailableSlot> getAvailableSlots(int slotDuration, List<Integer> providerIds) {
        Integer[] providerArray = providerIds.toArray(new Integer[0]);
        return appointmentSlotRepository.findAvailableSlotsByProviders(slotDuration,providerArray);
    }
}
