package com.amandevel.spring.salonapi.service;

import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import java.util.List;
import java.util.Optional;

public interface SalonService {
    List<SalonServiceDetail> retrieveAllSalonServices();
    boolean checkServiceExists(long serviceId);
    Optional<SalonServiceDetail> retrieveSalonServiceDetailById (Long serviceId);
}
