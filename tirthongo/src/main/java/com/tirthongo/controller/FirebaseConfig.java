package com.tirthongo.controller;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConfig {
    static{
        try {
            initialize();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void initialize() throws Exception{
        FileInputStream serviceAccount =
new FileInputStream("demo\\src\\main\\resources\\images\\tirthOnGo_sdk.json");

FirebaseOptions options = new FirebaseOptions.Builder()
  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
  .setStorageBucket("javafx-dce2b.appspot.com")
  .build();

FirebaseApp.initializeApp(options);
System.out.println("Successfully Initialized");

    }
}
