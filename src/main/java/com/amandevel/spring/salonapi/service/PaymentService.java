package com.amandevel.spring.salonapi.service;

import java.util.Optional;

import com.amandevel.spring.salonapi.model.Payment;
import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.model.request.ServicePaymentRequest;

public interface PaymentService {
    Optional<Payment>  initiateServicePayment (ServicePaymentRequest paymentRequest, SalonServiceDetail serviceDetail, Slot slot);
    Optional<Payment> paymentStatus (Payment payment);
    boolean checkIfPaymentExists(long paymentID);
    Optional<Payment> findPaymentById(long paymentId);
    Optional<Payment> findPaymentByIntentId (String intentId);
}