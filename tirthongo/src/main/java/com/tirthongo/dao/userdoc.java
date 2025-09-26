// package com.tirthongo.dao;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;

// public class userdoc {

//     private static final String PROJECT_ID = "javafx-dce2b";
//     private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";

//     public static boolean signUpWithEmailAndPasswordUser (String email, String password){

//       try{
//       URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
//       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//       conn.setRequestMethod("POST");
//       conn.setRequestProperty("Content-Type","application/json");
//       conn.setDoOutput(true);

//       String payload = String.format(
//         "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
//         email,password
//       );
 
//       OutputStream os = null;

//       os= conn.getOutputStream();
//       os.write(payload.getBytes());

//       int responseCode = conn.getResponseCode();
//         if(responseCode == 200){
//             //Login Successfull
//             return true;

//         }else{
//             //Login Failed
//             try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
//                 String line;
//                 while((line = br.readLine()) != null){
//                     System.out.println(line);
//                 }
//             }
//             return false;
//         }

//      }catch(Exception exception){

//      exception.printStackTrace();
//      return false;
//      }
// }

//     // 2. Firestore मध्ये user document create करण्याचा method
//     public static boolean createUserInFirestore(String email, String collection) {
//         try {
//             String endpoint = String.format(
//                     "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s?key=%s",
//                     PROJECT_ID, collection, API_KEY);

//             String jsonPayload = String.format("""
//                 {
//                     "fields": {
//                         "email": { "stringValue": "%s" },
//                     }
//                 }
//                 """, email);

//             URL url = new URL(endpoint);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setRequestProperty("Content-Type", "application/json");
//             conn.setDoOutput(true);

//             try (OutputStream os = conn.getOutputStream()) {
//                 os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
//             }

//             int responseCode = conn.getResponseCode();
//             conn.disconnect();

//             return responseCode == 200 || responseCode == 202;

//         } catch (Exception e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     // 3. Combined signup method: Auth user create + Firestore doc create
//     public static boolean signUpUser(String email, String password) {
//         boolean authCreated = signUpWithEmailAndPasswordUser(email, password);
//         if (authCreated) {
//             boolean firestoreCreated = createUserInFirestore(email, "Travellers");
//             if (firestoreCreated) {
//                 System.out.println("User created successfully in Auth and Firestore");
//                 return true;
//             } else {
//                 System.out.println("User created in Auth but failed to create Firestore document");
//                 return false;
//             }
//         } else {
//             System.out.println("Failed to create user in Firebase Authentication");
//             return false;
//         }
//     }
// }






package com.tirthongo.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.tirthongo.model.UserProfileModel;

public class userdoc {

    private static final String PROJECT_ID = "javafx-dce2b";
    private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";

    public static boolean signUpWithEmailAndPasswordUser (String email, String password){

      try{
      URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type","application/json");
      conn.setDoOutput(true);

      String payload = String.format(
        "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
        email,password
      );
 
      OutputStream os = null;

      os= conn.getOutputStream();
      os.write(payload.getBytes());

      int responseCode = conn.getResponseCode();
        if(responseCode == 200){
            //Login Successfull
            return true;

        }else{
            //Login Failed
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
                String line;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }
            return false;
        }

     }catch(Exception exception){

     exception.printStackTrace();
     return false;
     }
}

    // 2. Firestore मध्ये user document create करण्याचा method
    public static boolean createUserInFirestore(String email, String collection) {
        try {
            String endpoint = String.format(
                    "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s?key=%s",
                    PROJECT_ID, collection, API_KEY);

            String jsonPayload = String.format("""
                {
                    "fields": {
                        "email": { "stringValue": "%s" },
                    }
                }
                """, email);

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            conn.disconnect();

            return responseCode == 200 || responseCode == 202;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Combined signup method: Auth user create + Firestore doc create
    public static boolean signUpUser(String email, String password) {
        boolean authCreated = signUpWithEmailAndPasswordUser(email, password);
        if (authCreated) {
            boolean firestoreCreated = createUserInFirestore(email, "Travellers");
            if (firestoreCreated) {
                System.out.println("User created successfully in Auth and Firestore");
                return true;
            } else {
                System.out.println("User created in Auth but failed to create Firestore document");
                return false;
            }
        } else {
            System.out.println("Failed to create user in Firebase Authentication");
            return false;
        }
    }

    // UPDATED: Get UserProfileModel by EMAIL (for backward compatibility)
    public static UserProfileModel getUserProfileModel(String email){
        System.out.println("=== getUserProfileModel called with email: " + email + " ===");
        try {
            ApiFuture<QuerySnapshot> querySnap = FirestoreClient.getFirestore()
                .collection("Travellers")
                .whereEqualTo("email", email)
                .get();
            
            if (!querySnap.get().getDocuments().isEmpty() && querySnap.get().getDocuments().get(0).exists()) {
                UserProfileModel userProfileModel = querySnap.get().getDocuments().get(0).toObject(UserProfileModel.class);
                System.out.println("Found user profile for email: " + email);
                System.out.println("User details: " + userProfileModel.getEmail());
                return userProfileModel;
            } else {
                System.out.println("No user profile found for email: " + email);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error fetching user profile by email: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // NEW: Get UserProfileModel by DOCUMENT ID (UID)
    public static UserProfileModel getUserProfileModelByUid(String uid) {
        System.out.println("=== getUserProfileModelByUid called with UID: " + uid + " ===");
        try {
            ApiFuture<DocumentSnapshot> docSnap = FirestoreClient.getFirestore()
                .collection("Travellers")
                .document(uid)
                .get();
            
            DocumentSnapshot document = docSnap.get();
            if (document.exists()) {
                UserProfileModel userProfileModel = document.toObject(UserProfileModel.class);
                if (userProfileModel != null) {
                    userProfileModel.setDocumentId(uid); // Set the document ID
                    System.out.println("Found user profile for UID: " + uid);
                    System.out.println("User email: " + userProfileModel.getEmail());
                    return userProfileModel;
                } else {
                    System.out.println("Document exists but failed to convert to UserProfileModel for UID: " + uid);
                    return null;
                }
            } else {
                System.out.println("No user profile document found for UID: " + uid);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error fetching user profile by UID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // NEW: Check if user profile exists by UID
    public static boolean userProfileExists(String uid) {
        System.out.println("=== Checking if user profile exists for UID: " + uid + " ===");
        try {
            ApiFuture<DocumentSnapshot> docSnap = FirestoreClient.getFirestore()
                .collection("Travellers")
                .document(uid)
                .get();
            
            DocumentSnapshot document = docSnap.get();
            boolean exists = document.exists();
            System.out.println("Profile exists for UID " + uid + ": " + exists);
            return exists;
            
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error checking if user profile exists: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // NEW: Create user document with UID as document ID
    public static boolean createUserWithUid(String uid, String email) {
        System.out.println("=== Creating user document with UID: " + uid + " and email: " + email + " ===");
        try {
            ApiFuture<WriteResult> writeResult = FirestoreClient.getFirestore()
                .collection("Travellers")
                .document(uid)
                .set(Map.of("email", email, "documentId", uid));
            
            writeResult.get(); // Wait for completion
            System.out.println("User document created successfully with UID: " + uid);
            return true;
            
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error creating user document: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // DEBUGGING: List all documents in Travellers collection
    public static void listAllTravellers() {
        System.out.println("=== Listing all documents in Travellers collection ===");
        try {
            ApiFuture<QuerySnapshot> querySnap = FirestoreClient.getFirestore()
                .collection("Travellers")
                .get();
            
            for (DocumentSnapshot document : querySnap.get().getDocuments()) {
                System.out.println("Document ID: " + document.getId());
                System.out.println("Document data: " + document.getData());
                System.out.println("---");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error listing travellers: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
