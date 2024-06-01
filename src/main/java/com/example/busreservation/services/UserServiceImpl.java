package com.example.busreservation.services;

import com.example.busreservation.exception.EmailNotConfirmed;
import com.example.busreservation.model.User;
import com.example.busreservation.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public User addUser(User user) {
        User foundUser=userRepo.findOneByEmail(user.getEmail());
        if(foundUser!=null){
            log.error("User already exists");
            throw new RuntimeException();
        }
        User user1=new User();
        user1.setEmail(user.getEmail());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user1.setActivated(false);
        user1.setWalletBalance(BigDecimal.valueOf(0.0));
        user1.setActivationHash(UUID.randomUUID().toString());
        user1.setRole("ROLE_USER");

        return userRepo.save(user1);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findOneByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void saveUser(User user) {
        log.info("Saving user {}",user);
        userRepo.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    //need to implement for authentication
    @Override
    public void activateEmail(String activationHash) {
        User user = userRepo.findOneByActivationHash(activationHash);
        if (user == null) {
            throw new EmailNotConfirmed();
        }
        else{
            user.setActivated(true);
            userRepo.save(user);
        }
    }

    @Override
    public String sendConfirmationEmailToUser(User user, String baseUrl) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            log.info(user.getActivationHash());
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("nirajmuttur@gmail.com");
            helper.setSubject("Confirm your e-mail address");
            // TODO: Zmienić adres na frontend i wysłać zapytanie http z frontu
            helper.setText(
                    "<a href=\"" + baseUrl + "/api/activate-account?h=" + user.getActivationHash() + "\">" +
                            "Click here" +"to confirm your e-mail address"+
                            "</a>",
                    true);
            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Cannot send confirmation e-mail: " + e.getMessage()); // TODO: Dodać logowanie
        }
        return "Conformation Mail is sent";
    }

    @Override
    public User addMoney(BigDecimal a, String id) {
        User user=userRepo.findOneByEmail(id);
        user.setWalletBalance(user.getWalletBalance().add(a));
        return userRepo.save(user);

    }

    @Override
    public BigDecimal getWalletBalance(String email) {
        return userRepo.getWalletAmount(email);
    }

    @Override
    public void deductMoney(BigDecimal a, String id) {
        User user=userRepo.findOneByEmail(id);
        user.setWalletBalance(user.getWalletBalance().subtract(a));
        userRepo.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser=userRepo.findOneByEmail(username);
        if(foundUser==null){
            throw new UsernameNotFoundException("User not found");
        }
        if(!foundUser.isActivated()){
            throw new EmailNotConfirmed("Email not confirmed");
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(foundUser.getRole()));
        log.info(authorities.toString());
        return new org.springframework.security.core.userdetails.User(username, foundUser.getPassword(),
                true, true, true, foundUser.isActivated(),
                authorities);
    }






}
