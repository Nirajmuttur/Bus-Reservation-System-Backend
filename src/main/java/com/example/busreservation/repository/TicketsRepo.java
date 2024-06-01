package com.example.busreservation.repository;

import com.example.busreservation.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketsRepo extends JpaRepository<Tickets,Long> {
    List<Tickets> findByUserId(Long userId);
}
