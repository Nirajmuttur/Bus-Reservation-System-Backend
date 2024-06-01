package com.example.busreservation.controller;

import com.example.busreservation.model.Bus;
import com.example.busreservation.repository.BusRepo;
import com.example.busreservation.services.BusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class BusController {
    @Autowired
    private BusService busService;


    @PostMapping(value = "/admin/addbus")
    public Bus saveBus(@RequestBody Bus bus){
        log.info(bus.toString());
        return busService.addBus(bus);
    }
    @PostMapping(value = "/admin/updateBus")
    public void updateBus(@RequestBody Bus bus){
        busService.updateBus(bus);
    }

    @GetMapping(value = "/admin/getBuses")
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }
    @GetMapping(value="/getBuses/{id}")
    public Optional<Bus> getBusById(@PathVariable("id") Long bus_id){
        return busService.getBusbyId(bus_id);
    }
    @PostMapping(value="/admin/deleteBus")
    public void deleteBus(@RequestBody Bus bus){
        busService.deleteBus(bus);
    }

    @GetMapping(value="/user/check/{sr}/{ds}/{d}")
    public List<Bus> BusAvailabilityCheck(@PathVariable("sr") String sr,@PathVariable("ds") String ds,@PathVariable("d") String d) {
//        log.info("date {}",d);
//        Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(d);
//        log.info("date2 {}",date1);
        List<Bus> l1=busService.BusAvailability(sr,ds,d);
        return l1;
//        if(l1.size()<=0){
//            l1.add(null);
//            return l1;
//        }
//        else
//            return l1;
    }

}
