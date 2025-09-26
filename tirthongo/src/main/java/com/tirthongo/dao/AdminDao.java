// package com.tirthongo.dao;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.concurrent.ExecutionException;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.QuerySnapshot;
// import com.google.firebase.cloud.FirestoreClient;
// import com.tirthongo.model.AdminProfileModel;

// public class AdminDao {

//     private static final String PROJECT_ID = "javafx-dce2b";
//     private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";

//     // 1. Firebase Authentication मध्ये Admin User create करण्याचा method (using REST API)
//     public static boolean signUpAdminWithEmailAndPasswordAdmin (String email, String password){
//         try{
//             URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setRequestProperty("Content-Type","application/json");
//             conn.setDoOutput(true);

//             String payload = String.format(
//                 "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
//                 email, password
//             );
        
//             try (OutputStream os = conn.getOutputStream()) {
//                 os.write(payload.getBytes(StandardCharsets.UTF_8));
//             }

//             int responseCode = conn.getResponseCode();
//             if(responseCode == HttpURLConnection.HTTP_OK){ // 200 OK
//                 System.out.println("Admin user created successfully in Firebase Auth.");
//                 return true;
//             } else {
//                 System.err.println("Failed to create admin user in Firebase Auth. Response Code: " + responseCode);
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
//                     String line;
//                     StringBuilder errorResponse = new StringBuilder();
//                     while((line = br.readLine()) != null) {
//                         errorResponse.append(line);
//                     }
//                     System.err.println("Firebase Auth Error: " + errorResponse.toString());
//                 }
//                 return false;
//             }

//         } catch(Exception exception){
//             exception.printStackTrace();
//             System.err.println("Exception during Firebase Auth admin sign-up: " + exception.getMessage());
//             return false;
//         }
//     }

//     // 2. Firestore मध्ये Admin document create करण्याचा method
//     public static boolean createAdminInFirestore(String email) {
//         try {
//             // Using a dedicated 'admins' collection for admin profiles
//             String endpoint = String.format(
//                 "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s?key=%s",
//                 PROJECT_ID, "admins", API_KEY); // Collection name is "admins"

//             // Admin-specific fields. Added 'isAdmin' boolean for application logic.
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

//             if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { // 200 OK or 201 Created
//                 System.out.println("Admin document created successfully in Firestore 'admins' collection.");
//                 return true;
//             } else {
//                 System.err.println("Failed to create admin document in Firestore. Response Code: " + responseCode);
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
//                     String line;
//                     StringBuilder errorResponse = new StringBuilder();
//                     while((line = br.readLine()) != null) {
//                         errorResponse.append(line);
//                     }
//                     System.err.println("Firestore Error: " + errorResponse.toString());
//                 }
//                 return false;
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//             System.err.println("Exception during Firestore admin document creation: " + e.getMessage());
//             return false;
//         }
//     }

//     // 3. Combined signup method: Auth admin create + Firestore doc create
//     public static boolean signUpAdmin(String email, String password) {
//         boolean authCreated = signUpAdminWithEmailAndPasswordAdmin(email, password);
//         if (authCreated) {
//             boolean firestoreCreated = createAdminInFirestore(email); // Use 'admins' collection
//             if (firestoreCreated) {
//                 System.out.println("Admin created successfully in Auth and Firestore.");
//                 return true;
//             } else {
//                 System.out.println("Admin created in Auth but failed to create Firestore document.");
//                 // Consider deleting the Firebase Auth user here if Firestore creation fails
//                 // (requires Admin SDK or a separate REST call to delete user, which also needs special auth)
//                 return false;
//             }
//         } else {
//             System.out.println("Failed to create admin in Firebase Authentication.");
//             return false;
//         }
//     }
  
//     public static AdminProfileModel getAdminProfileModel(String docId){
//        try {
//         ApiFuture<QuerySnapshot> querySnap = FirestoreClient.getFirestore().collection("admins").whereEqualTo("email", docId).get();
//         if(querySnap.get().getDocuments().get(0).exists()){
//             AdminProfileModel adminProfileModel = querySnap.get().getDocuments().get(0).toObject(AdminProfileModel.class);
//             System.out.println("In UserDoc"+adminProfileModel.getEmail());
//             return adminProfileModel;
            
//         }
//        } catch (InterruptedException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//        } catch (ExecutionException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//        }
//         return null;
//     }
// }

package com.tirthongo.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
// Removed unused Admin SDK imports (ApiFuture, DocumentSnapshot, QuerySnapshot, FirestoreClient, ExecutionException)
// Removed unused FirebaseInitializer import (should be in TirthOnGoApp)
// Removed unused UserProfileModel import (not used in AdminDao)

public class AdminDao {

    private static final String PROJECT_ID = "javafx-dce2b";
    private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M"; // Verify your API Key

    /**
     * Authenticates a new Admin user with Firebase Authentication (REST API).
     * This method only handles Auth, it does NOT create the Firestore document.
     * The generated UID is handled by SignUpController.
     *
     * @param email The admin's email.
     * @param password The admin's password.
     * @return true if Auth signup is successful, false otherwise.
     */
    public static boolean signUpAdminWithEmailAndPasswordAdmin (String email, String password){
        try{
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);

            String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                email, password
            );
        
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){ // 200 OK
                System.out.println("Admin user created successfully in Firebase Auth (via AdminDao).");
                return true; // UID is parsed by SignUpController, not here
            } else {
                System.err.println("Failed to create admin user in Firebase Auth. Response Code: " + responseCode);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    String line;
                    StringBuilder errorResponse = new StringBuilder();
                    while((line = br.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.err.println("Firebase Auth Error: " + errorResponse.toString());
                }
                return false;
            }

        } catch(Exception exception){
            exception.printStackTrace();
            System.err.println("Exception during Firebase Auth admin sign-up (via AdminDao): " + exception.getMessage());
            return false;
        }
    }

    /**
     * Creates or updates an Admin document in the 'admins' Firestore collection,
     * using the provided UID as the document ID. This is for initial profile creation.
     *
     * @param documentId The UID of the Admin (from Firebase Auth).
     * @param email The email of the Admin.
     * @return true if the initial document creation/update was successful, false otherwise.
     */
    public static boolean createAdminInFirestore(String documentId, String email) { // FIXED: Takes documentId
        try {
            // Endpoint now specifies the document ID directly, ensuring UID is used as document ID
            String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s",
                PROJECT_ID, "admins", documentId, API_KEY); 

            String jsonPayload = String.format("""
                {
                    "fields": {
                        "email": { "stringValue": "%s" },
                        "isAdmin": { "booleanValue": true }
                    }
                }
                """, email);
            
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Use PATCH or PUT to create/overwrite a document with a specific ID.
            // PATCH is generally preferred as it supports partial updates, even for creation.
            //conn.setRequestMethod("PATCH"); // Uses PATCH method
            // If running on JDK 8 (which you said you fixed), you might need:
            conn.setRequestMethod("POST"); 
            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH"); 

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            conn.disconnect();

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK for PATCH/PUT
                System.out.println("Firestore initial admin document created/updated for admins/" + documentId + " via AdminDao.");
                return true;
            } else {
                System.err.println("Failed to create initial admin Firestore document for admins/" + documentId + ". Response Code: " + responseCode);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    String line;
                    StringBuilder errorResponse = new StringBuilder();
                    while((line = br.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.err.println("Firestore Create Error: " + errorResponse.toString());
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception creating initial admin Firestore document: " + e.getMessage());
            return false;
        }
    }
    
    
}