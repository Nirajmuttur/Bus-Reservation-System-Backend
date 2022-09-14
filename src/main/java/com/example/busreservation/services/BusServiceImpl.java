package com.example.busreservation.services;

import com.example.busreservation.model.Bus;
import com.example.busreservation.repository.BusRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class BusServiceImpl implements BusService{
    @Autowired
    BusRepo busRepo;

    SimpleDateFormat f1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Bus addBus(Bus bus)  {
//        Bus findBus=busRepo.getOne(bus.getBus_id());
//
//        if(findBus!=null){
//            log.error("Bus already exists");
//        }
        Bus b1=new Bus();
        b1.setBus_id(bus.getBus_id());
        b1.setSource(bus.getSource());
        b1.setDestination(bus.getDestination());
        b1.setSeats(bus.getSeats());
        b1.setDistance(bus.getDistance());
        b1.setType(bus.getType());
        b1.setTime((bus.getTime()));
        b1.setPrice(bus.getPrice());
        b1.setDepartureDate(bus.getDepartureDate());

        return busRepo.save(b1);

    }


    @Override
    public Optional<Bus> getBusbyId(Long bus_id) {
        return busRepo.findById(bus_id);
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }

    @Override
    public void saveBus(Bus bus) {
        log.info("Saving bus");
        busRepo.save(bus);
    }

    @Override
    public void updateBus(Bus bus) {
        log.info("Updating Bus");
        busRepo.save(bus);
    }

    @Override
    public void deleteBus(Bus bus) {
        busRepo.delete(bus);
    }

    @Override
    public List<Bus> BusAvailability(String sr, String ds, String d) {
        return busRepo.BusAvailabilty(sr,ds,d);
    }

    @Override
    public void updateSeats(int seats, Long id) {
        busRepo.updateSeats(seats,id);
    }
}
