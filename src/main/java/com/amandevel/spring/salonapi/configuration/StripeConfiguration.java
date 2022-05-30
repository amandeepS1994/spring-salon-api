package com.amandevel.spring.salonapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Configuration
public class StripeConfiguration {
    private final String stripeApiKey;

    public StripeConfiguration (@Value("${stripe.key}") String key) {
        this.stripeApiKey = key;
    }
}
