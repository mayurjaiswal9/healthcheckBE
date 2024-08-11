package com.healthcheck.gcc.persistance.repository;

import com.healthcheck.gcc.dto.AvailableSlot;
import com.healthcheck.gcc.persistance.entity.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    @Query(value = "SELECT provider_id, slot_date, start_time, end_time " +
            "FROM get_available_slots_for_providers(:slotDuration, :providerList)", nativeQuery = true)
    List<Object[]> findAvailableSlotsByProvidersObject(@Param("slotDuration") int slotDuration,
                                                       @Param("providerList") Integer[] providerList);

    default List<AvailableSlot> findAvailableSlotsByProviders(int slotDuration, Integer[] providerList) {
        List<Object[]> results = findAvailableSlotsByProvidersObject(slotDuration, providerList);
        return results.stream()
                .map(result -> AvailableSlot.builder()
                        .providerId(((Number) result[0]).longValue())
                        .slotDate(((java.sql.Date) result[1]).toLocalDate())
                        .startTime(((java.sql.Timestamp) result[2]).toLocalDateTime())
                        .endTime(((java.sql.Timestamp) result[3]).toLocalDateTime())
                        .build())
                .toList();
    }


}
