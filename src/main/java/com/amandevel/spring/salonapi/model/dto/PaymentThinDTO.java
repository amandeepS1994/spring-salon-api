package com.amandevel.spring.salonapi.model.dto;

import java.time.LocalDateTime;

import com.amandevel.spring.salonapi.model.enumeration.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentThinDTO {
    private Long id;
    private SalonServiceDetailDTO serviceDetail;
    private SlotDTOThin slot;
    private double payment;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String secretId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String intentId;
}
