package com.example.busreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email_Not_confirmed")
public class EmailNotConfirmed extends RuntimeException{
    public EmailNotConfirmed(){
    }
    public EmailNotConfirmed(String msg){
        super(msg);
    }
}
