package com.amandevel.spring.salonapi.controller;

import java.util.List;

import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import com.amandevel.spring.salonapi.model.response.ApiResponse;
import com.amandevel.spring.salonapi.service.SalonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("service/")
public class ServiceController {

    private final SalonService salonService;

    public ServiceController (SalonService salonService) {
        this.salonService = salonService;
    }

    //Get Salon details

    @GetMapping("available/")
    public ResponseEntity<ApiResponse<List<SalonServiceDetail>>> retrieveSalonDetails () {
        return new ResponseEntity<>(new ApiResponse(true, salonService.retrieveAllSalonServices()), HttpStatus.ACCEPTED);

    }
}
