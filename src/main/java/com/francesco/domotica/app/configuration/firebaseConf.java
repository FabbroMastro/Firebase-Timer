package com.francesco.domotica.app.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
    Date date;
    Timer rtimer;
    long finish;

    @Bean
    public void firebaseConfing() throws IOException {
        FileInputStream refreshToken = new FileInputStream("domotica.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://domotica-eb57a-default-rtdb.firebaseio.com/")
                .build();
        if (app == null) {
            app = FirebaseApp.initializeApp(options);
        }
    }

    public void writeTimerToFirebase(FirebaseTimer timer) {
        // Get the Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference ref = database.getReference("relay");
        // Set the data
        ref.setValueAsync(0);

        rtimer = new Timer();

        // Set the Colombia time zone
        TimeZone tz = TimeZone.getTimeZone("America/Bogota");
        DateTimeZone dtz = DateTimeZone.forID(tz.getID());

        // Create a new Date for EndDate
        date = new Date(timer.getEnddate());

        // Create a timer task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(DateTime.now(dtz).toDate());
                // Check if the timer has expired
                if (date.compareTo(DateTime.now(dtz).toDate()) < 0) {
                    cancelTimer();
                }
            }
        };

        // Schedule the timer task
        rtimer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        // Get the Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference ref = database.getReference("relay");
        // Set the data
        date = null;
        rtimer.cancel();
        ref.setValueAsync(1);
    }

    public Date getTimer() {
        return date;
    }
}
