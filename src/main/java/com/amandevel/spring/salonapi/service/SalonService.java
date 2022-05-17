package com.amandevel.spring.salonapi.service;

import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SalonService {
    List<SalonServiceDetail> retrieveAllSalonServices();
}
