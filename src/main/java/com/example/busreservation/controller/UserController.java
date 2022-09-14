package com.example.busreservation.controller;

import com.example.busreservation.model.User;
import com.example.busreservation.repository.UserRepo;
import com.example.busreservation.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")//user is getting registered (dummy api for testing purpose)
    public ResponseEntity<Object> saveUser(@RequestBody User user , HttpServletRequest request){
        User user1=userService.addUser(user);
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        userService.sendConfirmationEmailToUser(user1, baseUrl);
        Map<String,String> msg=new HashMap<>();
        msg.put("message",userService.sendConfirmationEmailToUser(user1, baseUrl));
        return new ResponseEntity<Object>(msg,HttpStatus.OK);
    }

    @RequestMapping(value = "/activate-account", method = RequestMethod.GET)
    public void confirmEmail(@RequestParam("h") String activationHash) {
        userService.activateEmail(activationHash);
    }

    @GetMapping(value = "/admin/getUsers") //working
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/user/addon/wallet")
    public User addMoneytoUser(@RequestParam("amount") BigDecimal amount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(amount.toString());
        log.info(authentication.getName());
        return userService.addMoney(amount, authentication.getName());
    }

    @GetMapping(value = "/user/getWalletBalance")
    public BigDecimal getWalletBalance(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        return userService.getWalletBalance(authentication.getName());

    }

    @PutMapping(value = "/user/update/wallet")
    public String updateMoney(@RequestParam("amount") BigDecimal amount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deductMoney(amount,authentication.getName());
        return "Updated";
    }
}
