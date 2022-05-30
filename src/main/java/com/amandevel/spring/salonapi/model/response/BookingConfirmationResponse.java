package com.amandevel.spring.salonapi.model.response;

import com.amandevel.spring.salonapi.model.dto.SalonDTO;
import com.amandevel.spring.salonapi.model.dto.TicketDTOFat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class BookingConfirmationResponse {
    private SalonDTO salan;
    private TicketDTOFat ticket;
}
