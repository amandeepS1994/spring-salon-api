package com.amandevel.spring.salonapi.controller;

import java.util.Objects;
import java.util.Optional;

import com.amandevel.spring.salonapi.model.Payment;
import com.amandevel.spring.salonapi.model.Salon;
import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.model.dto.PaymentDTO;
import com.amandevel.spring.salonapi.model.dto.SalonDTO;
import com.amandevel.spring.salonapi.model.dto.TicketDTOFat;
import com.amandevel.spring.salonapi.model.dto.TicketDTOThin;
import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;
import com.amandevel.spring.salonapi.model.request.ServicePaymentRequest;
import com.amandevel.spring.salonapi.model.response.ApiResponse;
import com.amandevel.spring.salonapi.model.response.BookingConfirmationResponse;
import com.amandevel.spring.salonapi.service.PaymentService;
import com.amandevel.spring.salonapi.service.SalonService;
import com.amandevel.spring.salonapi.service.SlotService;
import com.amandevel.spring.salonapi.utility.ObjectMapperUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;


@RestController()
@RequestMapping(path = "payments/")
public class PaymentController {
    
    private final SalonService salonService;
    private final SlotService slotService;
    private final PaymentService paymentService;
    private final Salon salan;
    
    public PaymentController(SalonService salonService, SlotService slotService, PaymentService paymentService, Salon salon) {
        this.salonService = salonService;
        this.slotService = slotService;
        this.paymentService = paymentService;
        this.salan = salon;
    }


    @Operation(method = "Post", deprecated = false, description = "InitiatePaymentAPI")
    @PostMapping(path = "initiate/")
    public ResponseEntity<ApiResponse<Object>> intiatePayment(@RequestBody ServicePaymentRequest paymentRequest) {
        if (Objects.nonNull(paymentRequest)) {
            Optional<SalonServiceDetail> optionalServiceDetail = salonService.retrieveSalonServiceDetailById(paymentRequest.getSalonServiceDetailId());
            Optional<Slot> optionalSlot = slotService.findBySlotIdAndStatus(paymentRequest.getSlotId(), SlotStatus.AVAILABLE);
            if (optionalServiceDetail.isPresent() && optionalSlot.isPresent()) {
                Optional<Payment> optionalPayment =  paymentService.initiateServicePayment(paymentRequest, optionalServiceDetail.get(), optionalSlot.get());
                return optionalPayment.isPresent() ? new ResponseEntity<>(new ApiResponse<>(true, ObjectMapperUtil.mapEntity(optionalPayment.get(), PaymentDTO.class)), HttpStatus.ACCEPTED) : new ResponseEntity<>(new ApiResponse<>(false, "Failed to Initiate Payment."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "Service Id or Slot is Not Available."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "Incomplete Information."), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{paymentId}/confirm/")
    public ResponseEntity<ApiResponse<BookingConfirmationResponse>> confirmPayment (@PathVariable String paymentId) {
        if (Objects.nonNull(paymentId)) {
                Optional<Payment> optionalPayment = paymentService.findPaymentByIntentId(paymentId);
            if (optionalPayment.isPresent()) {
                Optional<Payment> servicePayment = paymentService.paymentStatus(optionalPayment.get());
                if (servicePayment.isPresent()) {
                    BookingConfirmationResponse bookingConfirmationResponse = BookingConfirmationResponse.builder()
                    .ticket(Objects.nonNull(servicePayment.get().getTicket()) ? ObjectMapperUtil.mapEntity(servicePayment.get().getTicket(), TicketDTOFat.class) : null)
                    .salan(ObjectMapperUtil.mapEntity(salan, SalonDTO.class))
                    .build();
                    return new ResponseEntity<>(new ApiResponse<>(true, bookingConfirmationResponse), HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>(new ApiResponse<>(false, "Payment Failed."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "Payment not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "Invalid Request"), HttpStatus.BAD_REQUEST);
    }
}
