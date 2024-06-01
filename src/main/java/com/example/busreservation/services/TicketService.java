package com.example.busreservation.services;

import com.example.busreservation.model.Tickets;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Tickets bookTicket(Long busId,String email,int numberOfSeats);
    List<Tickets> getAllTickets();
    List<Tickets> getAllTicketsOfUser(String email);
    Optional<Tickets> getTicketById(Long id);

    String cancelTickets(Long id);
}
