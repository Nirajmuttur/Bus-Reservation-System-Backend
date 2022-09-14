package com.example.busreservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"tickets"})
public class Bus {
    @Id
    private Long bus_id;

    private String source;
    private String destination;
    private int seats;
    private String time;
    private Long distance;
    private String type;
    private BigDecimal price;
    @NotNull
    private String departureDate;
    @OneToMany(mappedBy = "bus", cascade = CascadeType.REMOVE)
    private List<Tickets> tickets;
}
