package com.amandevel.spring.salonapi.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;
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

    @Override
    public boolean checkSlotIsAvailable(long slotId) {
        return slotRepository.existsByIdAndStatus(slotId, SlotStatus.AVAILABLE);
    }

    @Override
    public Optional<Slot> findBySlotIdAndStatus(long slotId, SlotStatus slotStatus) {
        return slotRepository.findByIdAndStatus(slotId, SlotStatus.AVAILABLE);
    }
    
}
