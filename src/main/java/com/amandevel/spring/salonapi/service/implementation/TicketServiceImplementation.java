package com.amandevel.spring.salonapi.service.implementation;

import java.util.Optional;

import com.amandevel.spring.salonapi.model.Ticket;
import com.amandevel.spring.salonapi.repository.TicketRepository;
import com.amandevel.spring.salonapi.service.TicketService;

import org.springframework.stereotype.Service;

@Service
public class TicketServiceImplementation implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImplementation (TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean checkIfTicketExists(long ticketId) {
        return ticketRepository.existsById(ticketId);
    }

    @Override
    public Optional<Ticket> retrieveTicketDetailsById(long ticketId) {
        return ticketRepository.findById(ticketId);
    }
    
}
