package com.francesco.domotica.app.configuration;

import java.io.FileInputStream;
import java.io.IOException;
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
    public void firebaseConfing() {
        try (FileInputStream refreshToken = new FileInputStream("/etc/secrets/domotica.json")) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://domotica-eb57a-default-rtdb.firebaseio.com/")
                    .build();
            if (app == null) {
                app = FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (FileInputStream refreshToken = new FileInputStream("domotica.json")) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://domotica-eb57a-default-rtdb.firebaseio.com/")
                    .build();
            if (app == null) {
                app = FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Bean
    public void listenTimer() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference ref = database.getReference("timer");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                timer = dataSnapshot.getValue(FirebaseTimer.class);               
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }
    public FirebaseTimer getTimer() {
        return timer;
    }

    public void writeTimerToFirebase(FirebaseTimer timer) {
        // Get the Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference ref = database.getReference("timer");
        // Set the data
        ref.setValueAsync(timer);
        
        Timer rtimer = new Timer();
        long seconds = timer.getStartdate();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deleteTimerFromFirebase();
            }
        };
        rtimer.schedule(task, seconds * 1000);
    }

    public void deleteTimerFromFirebase() {

        // Get the Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference ref = database.getReference("timer");

        // Delete the data
        ref.setValueAsync(null);
    }

}
