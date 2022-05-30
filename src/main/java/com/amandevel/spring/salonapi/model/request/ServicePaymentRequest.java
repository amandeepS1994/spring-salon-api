package com.amandevel.spring.salonapi.model.request;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ServicePaymentRequest {
    private long slotId;
    private long salonServiceDetailId;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    
}
