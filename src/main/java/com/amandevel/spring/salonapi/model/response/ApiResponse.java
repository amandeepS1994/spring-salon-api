package com.amandevel.spring.salonapi.model.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;
    private LocalDateTime responseTime;

    public ApiResponse (boolean status, String message) {
        this.status = status;
        this.message = message;
        this.responseTime = LocalDateTime.now();
    }


    public ApiResponse (boolean status,  T data ) {
        this.status = status;
        this.data = data;
        this.responseTime = LocalDateTime.now();
    }
}
