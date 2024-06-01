package com.example.busreservation.repository;

import com.example.busreservation.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface BusRepo extends JpaRepository<Bus,Long> {
    @Query(value="select * from bus where source=?1 and destination=?2 and departure_Date=?3",nativeQuery = true)
    public List<Bus> BusAvailabilty(String sr,  String ds,  String d);

    @Query(value="update bus set seats=seats+?1 where bus_id=?2",nativeQuery = true)
    public void  updateSeats(int seats,Long id);



}
//    where source=:s and destination:=ds and departuredate:=d