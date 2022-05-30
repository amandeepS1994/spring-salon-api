package com.amandevel.spring.salonapi.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.model.dto.SalonServiceDetailDTO;
import com.amandevel.spring.salonapi.model.response.ApiResponse;
import com.amandevel.spring.salonapi.service.SalonService;
import com.amandevel.spring.salonapi.service.SlotService;
import com.amandevel.spring.salonapi.utility.ObjectMapperUtil;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController()
@RequestMapping("service/")
public class ServiceController {

    private final SalonService salonService;
    private final SlotService slotService;

    public ServiceController (SalonService salonService, SlotService slotService) {
        this.salonService = salonService;
        this.slotService = slotService;
    }

    @GetMapping("available/")
    public ResponseEntity<ApiResponse<List<SalonServiceDetailDTO>>> retrieveSalonDetails () {
        return new ResponseEntity<>(new ApiResponse(true, ObjectMapperUtil.mapAllEntities(salonService.retrieveAllSalonServices(), SalonServiceDetailDTO.class)), HttpStatus.ACCEPTED);

    }

    @Operation(description = "Retrieves all availability for a given service by Id.")
    @GetMapping("{serviceId}/availability/")
    public ResponseEntity<ApiResponse<List<Slot>>> retrieveAvailableSlots (@PathVariable("serviceId") Long serviceId, @RequestParam("requestedDate") @DateTimeFormat(iso = ISO.DATE) LocalDate requestedDate) {
        if (Objects.nonNull(serviceId) && Objects.nonNull(requestedDate)) {
           List<Slot> availableSlots = slotService.availableServiceSlots(serviceId, requestedDate);
            return !availableSlots.isEmpty() ? new ResponseEntity<>(new ApiResponse<>(true, availableSlots), HttpStatus.ACCEPTED) : new ResponseEntity<>(new ApiResponse<>(false, "No Available Slots Found"), HttpStatus.NOT_FOUND);
          
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "Invalid Request"), HttpStatus.BAD_REQUEST);
    }
}
