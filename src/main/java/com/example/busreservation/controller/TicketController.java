package com.example.busreservation.controller;

import com.example.busreservation.model.Tickets;
import com.example.busreservation.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping(value = "/user/tickets/book/{bus_id}/{numberOfSeats}")
    public Tickets bookTicket(@PathVariable Long bus_id,@PathVariable int numberOfSeats){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ticketService.bookTicket(bus_id, authentication.getName(), numberOfSeats);
    }

    @RequestMapping(value = "/user/tickets", method = RequestMethod.GET)
    public List<Tickets> getAllCurrentUserTickets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ticketService.getAllTicketsOfUser(authentication.getName());
    }
    @RequestMapping(value = "/admin/tickets", method = RequestMethod.GET)
    public List<Tickets> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @RequestMapping(value = "/user/tickets/{id}/cancel", method = RequestMethod.DELETE)
    public ResponseEntity<Object> cancelTickets(@PathVariable Long id) {
//        log.info(tickets.toString());
        ticketService.cancelTickets(id);
        Map<String,String> msg=new HashMap<>();
        msg.put("message","Ticket Cancelled");
        return new ResponseEntity<Object>(msg, HttpStatus.OK);
    }
}
