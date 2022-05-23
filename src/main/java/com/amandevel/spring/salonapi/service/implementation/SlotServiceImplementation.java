package com.amandevel.spring.salonapi.service.implementation;

import java.time.LocalDate;
import java.util.List;

import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.repository.SlotRepository;
import com.amandevel.spring.salonapi.service.SlotService;

import org.springframework.stereotype.Service;

@Service
public class SlotServiceImplementation implements SlotService {

    private final SlotRepository slotRepository;

    public SlotServiceImplementation (SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public List<Slot> availableServiceSlots(Long serviceId, LocalDate localDateTime) {
        return slotRepository.findAllAvailableSlotsForService(serviceId, localDateTime, localDateTime.plusDays(1));    }
    
}
