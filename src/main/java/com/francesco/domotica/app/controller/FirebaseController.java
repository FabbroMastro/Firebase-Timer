package com.francesco.domotica.app.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;
import com.francesco.domotica.app.model.FirebaseTimer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "https://comforting-blini-f8927b.netlify.app/")
public class FirebaseController {

    @Autowired
    firebaseConf conf;
    Date date;

    
    @PostMapping("/timer")
    public ResponseEntity<String> createTimer(@RequestBody FirebaseTimer timer){
        conf.writeTimerToFirebase(timer);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/timer")
    public ResponseEntity<Date> getTimer() {
        date = conf.getTimer();
        return ResponseEntity.ok(date);
    }

    @GetMapping("/timer/delete")
    public ResponseEntity<String> deleteTimer() {
        conf.cancelTimer();
        return ResponseEntity.ok("OK");
    }
}
