package com.amandevel.spring.salonapi.model.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;



public class ApiErrorResponse {
    private HttpStatus status;
    private final LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;
 
    private ApiErrorResponse() {
        timestamp = LocalDateTime.now();
    }
 
    ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }
 
    ApiErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
 
    ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    
 }

}