// // package com.tirthongo.controller;

// // import java.io.BufferedReader;
// // import java.io.InputStreamReader;
// // import java.io.OutputStream;
// // import java.net.HttpURLConnection;
// // import java.net.URL;

// // public class LoginController {

    
// //     private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";

// //     public static boolean loginInWithEmailAndPassword (String email1, String password1){

// // try{
// //       URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
// //       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// //       conn.setRequestMethod("POST");
// //       conn.setRequestProperty("Content-Type","application/json");
// //       conn.setDoOutput(true);

// //       String payload = String.format(
// //         "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
// //         email1,password1
// //     );
 
// //       OutputStream os = null;

// //       os= conn.getOutputStream();
// //       os.write(payload.getBytes());

// //       int responseCode = conn.getResponseCode();
// //         if(responseCode == 200){
// //             //Login Successfull
// //             return true;

// //         }else{
// //             //Login Failed
// //             try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
// //                 String line;
// //                 while((line = br.readLine()) != null){
// //                     System.out.println(line);
// //                 }
// //             }
// //             return false;
// //         }

// //     }catch(Exception exception){

// //     exception.printStackTrace();
// //     return false;
// //     }
// //   }
// // }

// package com.tirthongo.controller;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import org.json.JSONObject; // Ensure you have this dependency in your pom.xml

// public class LoginController {

//     private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";
//     private static final String PROJECT_ID = "javafx-dce2b"; // Your Firebase project ID

//     // This class encapsulates the result of a login attempt.
//     public static class LoginResult {
//         public boolean success;
//         public String idToken;
//         public String localId; // User UID from Firebase Authentication
//         public String email;   // Email of the logged-in user
//         public String errorMessage;

//         public LoginResult(boolean success, String idToken, String localId, String email, String errorMessage) {
//             this.success = success;
//             this.idToken = idToken;
//             this.localId = localId;
//             this.email = email;
//             this.errorMessage = errorMessage;
//         }
//     }

//     public static LoginResult signInWithEmailAndPassword (String userEmail, String password){
//         try{
//             URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setRequestProperty("Content-Type","application/json");
//             conn.setDoOutput(true);

//             String payload = String.format(
//                 "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
//                 userEmail, password
//             );
        
//             try (OutputStream os = conn.getOutputStream()) {
//                 os.write(payload.getBytes(StandardCharsets.UTF_8));
//             }

//             int responseCode = conn.getResponseCode();
//             if(responseCode == HttpURLConnection.HTTP_OK){ // 200 OK
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
//                     StringBuilder response = new StringBuilder();
//                     String line;
//                     while ((line = br.readLine()) != null) {
//                         response.append(line);
//                     }
//                     JSONObject jsonResponse = new JSONObject(response.toString());
//                     String idToken = jsonResponse.getString("idToken");
//                     String localId = jsonResponse.getString("localId");
                    
//                     // Pass the email that was used for login into the LoginResult
//                     return new LoginResult(true, idToken, localId, userEmail, null); 
//                 }
//             } else {
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))){
//                     StringBuilder errorResponse = new StringBuilder();
//                     String line;
//                     while((line = br.readLine()) != null){
//                         errorResponse.append(line);
//                     }
//                     System.err.println("Firebase Auth Login Error: " + errorResponse.toString());
                    
//                     String errorMessage = "Login failed.";
//                     try {
//                         JSONObject errorJson = new JSONObject(errorResponse.toString());
//                         JSONObject errorDetail = errorJson.optJSONObject("error");
//                         if (errorDetail != null && errorDetail.has("message")) {
//                              errorMessage = errorDetail.getString("message");
//                         }
//                     } catch (Exception jsonEx) {
//                         System.err.println("Failed to parse Firebase error response: " + jsonEx.getMessage());
//                     }
//                     return new LoginResult(false, null, null, null, errorMessage);
//                 }
//             }
//         } catch(Exception exception){
//             exception.printStackTrace();
//             return new LoginResult(false, null, null, null, "An unexpected error occurred: " + exception.getMessage());
//         }
//     }

//     /**
//      * Checks if a document exists for a given user UID in a specified Firestore collection.
//      * This is used to determine if a user has a profile in 'Travellers' or 'admins' collection.
//      *
//      * @param collection The Firestore collection name (e.g., "Travellers" or "admins").
//      * @param documentId The ID of the document to check (user's UID).
//      * @return true if the document exists, false otherwise (including on error).
//      */
//     public static boolean checkIfDocumentExists(String collection, String documentId) {
//         try {
//             String endpoint = String.format(
//                 "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s",
//                 PROJECT_ID, collection, documentId, API_KEY);
            
//             URL url = new URL(endpoint);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("GET");
//             conn.setRequestProperty("Content-Type", "application/json");

//             int responseCode = conn.getResponseCode();
//             conn.disconnect();
            
//             // 200 OK means the document exists
//             return responseCode == HttpURLConnection.HTTP_OK;

//         } catch (Exception e) {
//             System.err.println("Error checking document existence in Firestore: " + e.getMessage());
//             return false;
//         }
//     }
// }






package com.tirthongo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject; // Ensure you have this dependency in your pom.xml

public class LoginController {

    private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";
    private static final String PROJECT_ID = "javafx-dce2b"; // Your Firebase project ID

    // This class encapsulates the result of a login attempt.
    public static class LoginResult {
        public boolean success;
        public String idToken;
        public String localId; // User UID from Firebase Authentication
        public String email;   // Email of the logged-in user
        public String errorMessage;

        public LoginResult(boolean success, String idToken, String localId, String email, String errorMessage) {
            this.success = success;
            this.idToken = idToken;
            this.localId = localId;
            this.email = email;
            this.errorMessage = errorMessage;
        }
    }

    public static LoginResult signInWithEmailAndPassword (String userEmail, String password){
        try{
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);

            String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                userEmail, password
            );
        
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){ // 200 OK
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String idToken = jsonResponse.getString("idToken");
                    String localId = jsonResponse.getString("localId");
                    
                    // Pass the email that was used for login into the LoginResult
                    return new LoginResult(true, idToken, localId, userEmail, null); 
                }
            } else {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))){
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null){
                        errorResponse.append(line);
                    }
                    System.err.println("Firebase Auth Login Error: " + errorResponse.toString());
                    
                    String errorMessage = "Login failed.";
                    try {
                        JSONObject errorJson = new JSONObject(errorResponse.toString());
                        JSONObject errorDetail = errorJson.optJSONObject("error");
                        if (errorDetail != null && errorDetail.has("message")) {
                             errorMessage = errorDetail.getString("message");
                        }
                    } catch (Exception jsonEx) {
                        System.err.println("Failed to parse Firebase error response: " + jsonEx.getMessage());
                    }
                    return new LoginResult(false, null, null, null, errorMessage);
                }
            }
        } catch(Exception exception){
            exception.printStackTrace();
            return new LoginResult(false, null, null, null, "An unexpected error occurred: " + exception.getMessage());
        }
    }

    
    public static boolean checkIfDocumentExists(String collection, String documentId) {
        try {
            String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s",
                PROJECT_ID, collection, documentId, API_KEY);
            
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            conn.disconnect();
            
            // 200 OK means the document exists
            return responseCode == HttpURLConnection.HTTP_OK;

        } catch (Exception e) {
            System.err.println("Error checking document existence in Firestore: " + e.getMessage());
            return false;
        }
    }
}
