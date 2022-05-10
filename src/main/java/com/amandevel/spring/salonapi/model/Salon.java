package com.amandevel.spring.salonapi.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:salon.application.properties")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salon {
    @Value("${salan.name}")
    private String name;
    @Value("${salan.address}")
    private String address;
    @Value("${salan.city}")
    private String city;
    @Value("${salan.state}")
    private String state;
    @Value("${salan.zipcode}")
    private String zipcode;
    @Value("${salan.phone}")
    private String phone;

}
