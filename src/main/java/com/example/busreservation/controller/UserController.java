package com.example.busreservation.controller;

import com.example.busreservation.model.User;
import com.example.busreservation.repository.UserRepo;
import com.example.busreservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")//user is getting registered (dummy api for testing purpose)
    public String saveUser(@RequestBody User user , HttpServletRequest request){
        User user1=userService.addUser(user);
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        return userService.sendConfirmationEmailToUser(user1, baseUrl);

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
    public String addMoneytoUser(){
       userService.addMoney();
        return "Success";
    }

//    @PutMapping(value = "/user/update/wallet")
//    public String updateMoney(Long id, BigDecimal a){
//        userRepo.deductMoney(id,a);
//        return "Updated";
//    }
}
