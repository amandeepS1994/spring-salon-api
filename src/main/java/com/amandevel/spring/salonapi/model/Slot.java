package com.amandevel.spring.salonapi.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.amandevel.spring.salonapi.model.enumeration.SlotStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Slot {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<SalonServiceDetail> availableDetails;
    @ManyToOne
    private SalonServiceDetail selectedService;
    private String stylistName;
    private LocalDateTime slotFor;
    private SlotStatus status;
    private LocalDateTime lockedAt;
    private LocalDateTime confirmedAt;
}
