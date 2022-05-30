package com.amandevel.spring.salonapi.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotDTOFull {
    
    private long id;
    private SalonServiceDetailDTO selectedService;
    private String stylistName;
    private LocalDateTime slotFor;
    private SlotStatus status;
    private LocalDateTime lockedAt;
    private LocalDateTime confirmedAt;
    private PaymentDTO payment;
}
