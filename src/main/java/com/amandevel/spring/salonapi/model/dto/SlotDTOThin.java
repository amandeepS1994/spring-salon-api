package com.amandevel.spring.salonapi.model.dto;

import java.time.LocalDateTime;

import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotDTOThin {
    
    private long id;
    private String stylistName;
    private LocalDateTime slotFor;
    private SlotStatus status;
    private LocalDateTime lockedAt;
    private LocalDateTime confirmedAt;
}
