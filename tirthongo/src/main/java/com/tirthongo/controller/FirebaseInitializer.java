// package com.tirthongo.controller;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import java.io.FileInputStream;
// import java.io.IOException;

// public class FirebaseInitializer {

//     private static boolean initialized = false; // To track initialization status

//     public static void initialize() {
//         if (initialized) {
//             System.out.println("Firebase Admin SDK already initialized.");
//             return; // Exit if already initialized
//         }

//         try {
        
//             FileInputStream serviceAccount = new FileInputStream("tirthongo\\src\\main\\resources\\tirthOnGo_sdk.json"); // Example path

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .setDatabaseUrl("https://javafx-dce2b-default-rtdb.firebaseio.com")
//                     .setStorageBucket("javafx-dce2b.appspot.com")
//                     .build();

//             FirebaseApp.initializeApp(options);
//             initialized = true; // Mark as initialized
//             System.out.println("Firebase Admin SDK initialized successfully.");

//         } catch (IOException e) {
//             System.err.println("Error initializing Firebase Admin SDK: " + e.getMessage());
//             e.printStackTrace();
            
//         }
//     }
// }


package com.tirthongo.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseInitializer {
    
    private static boolean initialized = false;
    
    public static void initialize() {
        if (initialized) {
            System.out.println("Firebase Admin SDK already initialized.");
            return;
        }
        
        // Fixed resource path - remove 'src/main/resources/' prefix
        try (InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/firebase.json")) {
            
            if (serviceAccount == null) {
                System.err.println("Firebase service account key file 'firebase.json' not found in resources folder.");
                System.err.println("Make sure firebase.json is in src/main/resources/ directory");
                return;
            }
            
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://javafx-dce2b-default-rtdb.firebaseio.com")
                    .setStorageBucket("javafx-dce2b.appspot.com")
                    .build();
            
            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("Firebase Admin SDK initialized successfully.");
            
        } catch (IOException e) {
            System.err.println("Error initializing Firebase Admin SDK: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during Firebase Admin SDK initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static boolean isInitialized() {
        return initialized;
    }
}
