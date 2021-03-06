package com.amandevel.spring.salonapi.service.implementation;

import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import com.amandevel.spring.salonapi.repository.SalonServiceDetailRepository;
import com.amandevel.spring.salonapi.service.SalonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceImplementation implements SalonService {

    private final SalonServiceDetailRepository salonServiceDetailRepository;


    public SalonServiceImplementation(SalonServiceDetailRepository salonServiceDetailRepository) {
        this.salonServiceDetailRepository = salonServiceDetailRepository;
    }

    @Override
    public List<SalonServiceDetail> retrieveAllSalonServices() {
        return salonServiceDetailRepository.findAll();
    }

    @Override
    public boolean checkServiceExists(long serviceId) {
        return salonServiceDetailRepository.existsById(serviceId);
    }

    @Override
    public Optional<SalonServiceDetail> retrieveSalonServiceDetailById(Long serviceId) {
        return salonServiceDetailRepository.findById(serviceId);
    }
}
