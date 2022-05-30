package com.amandevel.spring.salonapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalonServiceDetailDTO {

    private long id;
    private String name;
    private String description;
    private Long price;
    private int timeInMinutes;
}