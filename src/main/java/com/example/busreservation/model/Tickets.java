package com.example.busreservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String ticketNumber;

    private BigDecimal ticketPrice;
    private boolean paymentStatus;
    private int numberOfSeats;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date reservationDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Bus bus;
}
