package com.amandevel.spring.salonapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;

public interface SlotService {
    List<Slot> availableServiceSlots(Long serviceId, LocalDate localDateTime);
    boolean checkSlotIsAvailable (long slotId);
    Optional<Slot> findBySlotIdAndStatus(long slotId, SlotStatus slotStatus);
}
