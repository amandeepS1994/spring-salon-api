package com.amandevel.spring.salonapi.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.amandevel.spring.salonapi.model.enumeration.PaymentStatus;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "selected_service_id")
    private SalonServiceDetail serviceDetail;
    @OneToOne(fetch = FetchType.EAGER)
    private Slot slot;
    @Column(name = "amount")
    private double payment;
    @Column(name = "status")
    private PaymentStatus paymentStatus;
    @CreatedDate
    @Column(name = "created")
    private LocalDateTime createdTime;
    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updatedTime;
    private String intentId;
    @Column(name = "client_secret")
    private String secretId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Ticket.class, mappedBy = "payment")
    private Ticket ticket;

}
