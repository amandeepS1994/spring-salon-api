package com.amandevel.spring.salonapi.repository;

import com.amandevel.spring.salonapi.model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    
}
