package com.amandevel.spring.salonapi.controller;

import java.util.Objects;
import java.util.Optional;

import com.amandevel.spring.salonapi.model.Ticket;
import com.amandevel.spring.salonapi.model.dto.TicketDTOFat;
import com.amandevel.spring.salonapi.model.response.ApiResponse;
import com.amandevel.spring.salonapi.service.TicketService;
import com.amandevel.spring.salonapi.utility.ObjectMapperUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket/")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(path = "{ticketId}/")
    public ResponseEntity<ApiResponse<TicketDTOFat>> retrieveTicketDetails (@PathVariable Long ticketId) {
        if (Objects.nonNull(ticketId)) {
            Optional<Ticket> optionalTicket = ticketService.retrieveTicketDetailsById(ticketId);
           
                return optionalTicket.isPresent() ? new ResponseEntity<>(new ApiResponse<>(true, ObjectMapperUtil.mapEntity(optionalTicket.get(), TicketDTOFat.class)), HttpStatus.ACCEPTED) :  new ResponseEntity<>(new ApiResponse<>(false, "Ticket Id Not Found."), HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "Ticket Id Not Provided."), HttpStatus.BAD_REQUEST);
    }

}
