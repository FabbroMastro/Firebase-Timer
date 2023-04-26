package com.francesco.domotica.app.configuration;

import java.io.FileInputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.francesco.domotica.app.model.Timer;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class firebaseConf {

    static FirebaseApp app;
    static Object object;
    Map<String, Object> timerCache = new HashMap<>();;


    @Bean
    public static void firebaseConfing() throws Exception {
        FileInputStream refreshToken = new FileInputStream(
                "C:\\Users\\Francesco\\Desktop\\app\\src\\main\\resources\\domotica.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://domotica-eb57a-default-rtdb.firebaseio.com/")
                .build();
        if (app == null) {
            app = FirebaseApp.initializeApp(options);
        }
    }
    @Bean
    public void listenTimer(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);

        DatabaseReference ref = database.getReference("timer");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    object = dataSnapshot.getValue();
                    timerCache.put("timer", object);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }

    public Map<String, Object> getTimerCache() {
        return timerCache;
    }

}
