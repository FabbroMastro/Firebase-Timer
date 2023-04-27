package com.francesco.domotica.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;
import com.francesco.domotica.app.model.Timer;

@RestController
public class FirebaseController {

    @Autowired
    firebaseConf conf;
    
    private Map<String, Timer> timerCache = new HashMap<>();

    @GetMapping("/timer")
    public Map<String, Timer> showtimer() {
        timerCache = conf.getTimerCache();
        return timerCache;
    }

}
