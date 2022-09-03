package com.example.busreservation.services;

import com.example.busreservation.model.User;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.math.BigDecimal;
import java.util.List;

public interface UserService
{
    User addUser(User user);
//    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void saveUser(User user);   //testing purpose
    void updateUser(User user);
    void deleteUserById(Long id);

    void activateEmail(String activationHash);
    String sendConfirmationEmailToUser(User user, String baseUrl);

    void addMoney();
//
//    void deductMoney(Long id, BigDecimal a);
}
