package com.amandevel.spring.salonapi.repository;

import java.util.Optional;

import com.amandevel.spring.salonapi.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByIntentId(String intentId);
}