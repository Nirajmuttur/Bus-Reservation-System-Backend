package com.example.busreservation.repository;

import com.example.busreservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findOneByEmail(String email);
    User findOneByActivationHash(String activationHash);


    @Query(value="select u from Users u ",nativeQuery = true)
    public List<User> addMoney();


//    @Modifying
//    @Query(value = "update users u set u.walletBalance=u.walletBalance - a where u.id=:i")
//    void deductMoney(@Param("i") Long Id, @Param("a") BigDecimal a);
}
