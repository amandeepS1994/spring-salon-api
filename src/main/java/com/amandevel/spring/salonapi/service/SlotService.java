package com.amandevel.spring.salonapi.service;

import java.time.LocalDate;
import java.util.List;

import com.amandevel.spring.salonapi.model.Slot;

public interface SlotService {
    List<Slot> availableServiceSlots(Long serviceId, LocalDate localDateTime);
}
