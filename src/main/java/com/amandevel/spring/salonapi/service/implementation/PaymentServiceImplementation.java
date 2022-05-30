package com.amandevel.spring.salonapi.service.implementation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.amandevel.spring.salonapi.configuration.StripeConfiguration;
import com.amandevel.spring.salonapi.model.Payment;
import com.amandevel.spring.salonapi.model.SalonServiceDetail;
import com.amandevel.spring.salonapi.model.Slot;
import com.amandevel.spring.salonapi.model.Ticket;
import com.amandevel.spring.salonapi.model.enumeration.PaymentStatus;
import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;
import com.amandevel.spring.salonapi.model.enumeration.TicketStatus;
import com.amandevel.spring.salonapi.model.request.ServicePaymentRequest;
import com.amandevel.spring.salonapi.repository.PaymentRepository;
import com.amandevel.spring.salonapi.repository.SlotRepository;
import com.amandevel.spring.salonapi.service.PaymentService;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
@Slf4j
public class PaymentServiceImplementation implements PaymentService {

    private final StripeConfiguration stripeConfiguration;
    private final SlotRepository slotRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImplementation (StripeConfiguration stripeConfiguration, SlotRepository slotRepository, PaymentRepository paymentRepository) {
      this.stripeConfiguration = stripeConfiguration;
      this.slotRepository = slotRepository;
      this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> initiateServicePayment(ServicePaymentRequest paymentRequest,SalonServiceDetail serviceDetail, Slot slot) {
        Stripe.apiKey = stripeConfiguration.getStripeApiKey();
        PaymentIntentCreateParams params =
          PaymentIntentCreateParams.builder()
            .setAmount(serviceDetail.getPrice())
            .setCurrency("gbp")
            .setAutomaticPaymentMethods(
              PaymentIntentCreateParams.AutomaticPaymentMethods
                .builder()
                .setEnabled(true)
                .build()
            ).setDescription(String.format("%s %n %s for %s minutes with %s on the %s", serviceDetail.getName(), serviceDetail.getDescription(), String.valueOf(serviceDetail.getTimeInMinutes()), slot.getStylistName(), String.valueOf(slot.getSlotFor())))
            .build();

           try {
              PaymentIntent paymentIntent = PaymentIntent.create(params);
              slot.setLockedAt(LocalDateTime.now());
              slot.setStatus(SlotStatus.LOCKED);
              slot.setPayment(processServicePayment(serviceDetail, slot, paymentIntent, paymentRequest));
              slot.setSelectedService(serviceDetail);
            return Optional.of(slotRepository.save(slot).getPayment());
          } catch (StripeException e) {
            log.error(e.getMessage());
          }

        return Optional.empty();


    }

    @Override
    public Optional<Payment> paymentStatus(Payment payment) {
        try {
          String paymentStatus = PaymentIntent.retrieve(payment.getIntentId()).getStatus();  
          switch (paymentStatus) {
            case "canceled":
              payment.setPaymentStatus(PaymentStatus.FAILED);
              payment.getSlot().setStatus(SlotStatus.AVAILABLE);
              break;
            case "succeeded":
              payment.setPaymentStatus(PaymentStatus.SUCCESS);
              payment.getSlot().setStatus(SlotStatus.CONFIRMED);
              createPaymentTicket(payment);
              break;
            default:
              payment.setPaymentStatus(PaymentStatus.PROCESSING);
              updatePayment(payment);
              break;
          }
          return Optional.ofNullable(paymentRepository.save(payment));
        } catch (StripeException e) {
          log.error(e.getMessage());
        }

      return Optional.empty();
    }

    @Override
    public boolean checkIfPaymentExists(long paymentID) {
      return paymentRepository.existsById(paymentID);
    }

    @Override
    public Optional<Payment> findPaymentByIntentId (String paymentId) {
      return paymentRepository.findByIntentId(paymentId);
    }

    private void updatePayment(Payment payment) {
      paymentRepository.save(payment);
    }

    private void createPaymentTicket (Payment payment) {
          payment.setTicket(Ticket.builder().payment(payment).ticketStatus(TicketStatus.BOOKED).build());
    }

    private Payment processServicePayment (SalonServiceDetail service, Slot slot, PaymentIntent paymentIntent, ServicePaymentRequest request) {
      return Payment.builder()
      .payment(paymentIntent.getAmount())
      .secretId(paymentIntent.getClientSecret())
      .createdTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(paymentIntent.getCreated()), ZoneId.systemDefault()))
      .email(request.getEmail())
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .intentId(paymentIntent.getId())
      .phoneNumber(request.getPhoneNumber())
      .paymentStatus(PaymentStatus.PROCESSING)
      .updatedTime(LocalDateTime.now())
      .serviceDetail(service)
      .slot(slot)
      .build();
    }

    @Override
    public Optional<Payment> findPaymentById(long paymentId) {
      return paymentRepository.findById(paymentId);
    }

    
}
