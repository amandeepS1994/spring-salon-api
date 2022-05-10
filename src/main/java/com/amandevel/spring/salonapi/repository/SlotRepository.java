package com.amandevel.spring.salonapi.repository;

import com.amandevel.spring.salonapi.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
