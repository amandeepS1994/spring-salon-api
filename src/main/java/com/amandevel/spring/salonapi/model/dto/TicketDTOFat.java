package com.amandevel.spring.salonapi.model.dto;

import com.amandevel.spring.salonapi.model.enumeration.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTOFat {
    private long id;
    private PaymentThinDTO payment;
    private TicketStatus ticketStatus;
}
