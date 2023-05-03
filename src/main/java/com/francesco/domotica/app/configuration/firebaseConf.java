package com.francesco.domotica.app.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.francesco.domotica.app.model.FirebaseTimer;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

@Configuration
public class firebaseConf {

    static FirebaseApp app;
    FirebaseTimer timer;    


    public firebaseConf() {
    }

    @Bean
    public void firebaseConfing() throws IOException  {
            FileInputStream refreshToken = new FileInputStream("domotica.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://domotica-eb57a-default-rtdb.firebaseio.com/")
                    .build();
            if (app == null) {
                app = FirebaseApp.initializeApp(options);
            }
    }

    public Date writeTimerToFirebase(FirebaseTimer timer) {
        // Get the Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference ref = database.getReference("relay");
        // Set the data
        ref.setValueAsync(0);
        
        Timer rtimer = new Timer();
        Date date = new Date();

        long finish =  timer.getEnddate() * 1000;
        
        date.setTime(date.getTime() + finish);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if(new Date().compareTo(date) == 0 ){
                    ref.setValueAsync(1);
                    rtimer.cancel();
                }
            }
        };
        rtimer.scheduleAtFixedRate(task,0,1000);
        return date;
    }


}
