package com.francesco.domotica.app.configuration;

import java.io.FileInputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class firebaseConf {

    static FirebaseApp app;

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
        System.out.println(app);
    }

    public static FirebaseApp getApp() {
        return app;
    }

}
