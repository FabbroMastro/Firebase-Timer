package com.francesco.domotica.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;
import com.francesco.domotica.app.model.FirebaseTimer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FirebaseController {

    @Autowired
    firebaseConf conf;
    

    @GetMapping("/timer")
    public Map<String, FirebaseTimer> showtimer() {
        FirebaseTimer timer = conf.getTimer();
        Map<String, FirebaseTimer> map = new HashMap<>();
        map.put("timer", timer);
        return map;
    }

    @PostMapping("/timer")
    public String createTimer(@RequestBody FirebaseTimer timer){
        
        conf.writeTimerToFirebase(timer);
        return "OK";
    }

    @GetMapping("/timer/delete")
    public String deleteTimer() {
        conf.deleteTimerFromFirebase();
        return "OK";
    
    }

}
