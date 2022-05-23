package com.amandevel.spring.salonapi.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.amandevel.spring.salonapi.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByAvailableDetails_IdAndSlotForBetween(Long id, LocalDateTime slotDate, LocalDateTime slotDateAfter); 
    
    @Query(value = "select s.id, s.confirmed_at, s.locked_at, s.slot_for, s.status, s.stylist_name, s.selected_service_id, ss.available_services_id from slot s inner join slot_available_services ss on ss.slot_id = s.id where s.slot_for between :slotStartDate AND :slotEndDate  AND ss.available_services_id = :serviceId", nativeQuery = true)
    List<Slot> findAllAvailableSlotsForService(@Param("serviceId") Long serviceId, @Param("slotStartDate") LocalDate slotStartDate,  @Param("slotEndDate") LocalDate slotEndDate);
}
