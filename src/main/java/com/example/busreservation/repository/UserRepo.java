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

    @Query(value = "select wallet_Balance from users where email=?1",nativeQuery = true)
    public BigDecimal getWalletAmount(String id);

//    @Query(value = "update users set wallet_Balance=5000 where email=?1 ",nativeQuery = true)
//    public int addMoney(BigDecimal a,String id);

    @Query(value = "update users set walletbalance=walletbalance-?1 where id=?2",nativeQuery = true)
    public String DeductMoney(BigDecimal a,Long id);


//    @Modifying
//    @Query(value = "update users u set u.walletBalance=u.walletBalance - a where u.id=:i")
//    void deductMoney(@Param("i") Long Id, @Param("a") BigDecimal a);
}
