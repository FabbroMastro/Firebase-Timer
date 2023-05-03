package com.francesco.domotica.app.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;
import com.francesco.domotica.app.model.FirebaseTimer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class FirebaseController {

    @Autowired
    firebaseConf conf;
    Date date;

    @PostMapping("/timer")
    public String createTimer(@RequestBody FirebaseTimer timer){
        
        conf.writeTimerToFirebase(timer);
        return "OK";
    }

    @GetMapping("/timer")
    public String getTimer() {
        date = conf.getTimer();
        return date.toString();
    }

    @GetMapping("/timer/delete")
    public String deleteTimer() {
        conf.cancelTimer();
        return "OK";
    }
    

}
