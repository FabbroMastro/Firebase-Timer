package com.francesco.domotica.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;

@RestController
public class FirebaseController {

    @Autowired
    firebaseConf conf;
    
    private Map<String, Object> timerCache = new HashMap<>();

    @GetMapping("/timer")
    public Map<String, Object> showtimer() {

        timerCache = conf.getTimerCache();
        return timerCache;
    }

}
