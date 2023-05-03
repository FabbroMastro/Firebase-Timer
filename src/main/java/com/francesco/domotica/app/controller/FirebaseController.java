package com.francesco.domotica.app.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;
import com.francesco.domotica.app.model.FirebaseTimer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FirebaseController {

    @Autowired
    firebaseConf conf;

    @PostMapping("/timer")
    public String createTimer(@RequestBody FirebaseTimer timer){
        
        Date date = conf.writeTimerToFirebase(timer);
        return date.toString();
    }

}
