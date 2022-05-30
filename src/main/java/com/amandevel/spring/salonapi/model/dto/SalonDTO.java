package com.amandevel.spring.salonapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalonDTO {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phone;

}
