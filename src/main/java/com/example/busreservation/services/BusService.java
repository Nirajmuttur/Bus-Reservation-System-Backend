package com.example.busreservation.services;

import com.example.busreservation.model.Bus;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BusService {
    Bus addBus(Bus bus);
    Optional<Bus> getBusbyId(Long bus_id);
    List<Bus> getAllBuses();
    void saveBus(Bus bus);
    void updateBus(Bus bus);
    void deleteBus(Bus bus);

    List<Bus> BusAvailability(String sr, String ds, String d);

    public void  updateSeats(int seats,Long id);
}
