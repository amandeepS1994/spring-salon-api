package com.amandevel.spring.salonapi.service;

import java.util.Optional;

import com.amandevel.spring.salonapi.model.Ticket;

public interface TicketService {
    boolean checkIfTicketExists (long ticketId);
    Optional<Ticket> retrieveTicketDetailsById(long ticketId);
}
