package com.francesco.domotica.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francesco.domotica.app.configuration.firebaseConf;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

@RestController
public class FirebaseController {

    private Map<String, Object> timerCache = new HashMap<>();
    private Object object;

    @GetMapping("/timer")
    public Map<String, Object> showtimer() throws IOException {
        FirebaseApp app;
        app = firebaseConf.getApp();
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        System.out.println(database);

        DatabaseReference ref = database.getReference("timer");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    object = dataSnapshot.getValue();
                    timerCache.put("timer", object);
                    System.out.println(object);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });
        
        return timerCache;
    }

}
