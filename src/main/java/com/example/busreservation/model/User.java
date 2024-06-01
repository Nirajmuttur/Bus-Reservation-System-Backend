package com.example.busreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"tickets"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;        //primary key is created by name id by default

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Column(nullable = false)
    private String role;

    private BigDecimal walletBalance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tickets> tickets;

    private boolean isActivated;
    private String activationHash;
}
