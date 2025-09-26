// package com.tirthongo.dao;

// import org.json.JSONObject;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.HashMap;
// import java.util.Map;

// public class ProfileDao {

//     private static final String PROJECT_ID = "javafx-dce2b";
//     private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";

//     public static boolean updateProfileData(String collection, String documentId, Map<String, Object> fieldsToUpdate) {
//         try {
//             String endpoint = String.format(
//                 "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s&updateMask.fieldPaths=",
//                 PROJECT_ID, collection, documentId, API_KEY
//             );

//             // Construct updateMask for the PATCH request
//             StringBuilder updateMask = new StringBuilder();
//             for (String fieldName : fieldsToUpdate.keySet()) {
//                 if (updateMask.length() > 0) {
//                     updateMask.append("&updateMask.fieldPaths=");
//                 }
//                 updateMask.append(fieldName);
//             }
//             endpoint += updateMask.toString();

//             URL url = new URL(endpoint);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST"); // Use PATCH for partial updates
//             conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//             conn.setRequestProperty("Content-Type", "application/json");
//             conn.setDoOutput(true);

//             // Build the JSON payload for fields
//             JSONObject jsonPayload = new JSONObject();
//             JSONObject fields = new JSONObject();
//             for (Map.Entry<String, Object> entry : fieldsToUpdate.entrySet()) {
//                 String fieldName = entry.getKey();
//                 Object value = entry.getValue();

//                 JSONObject fieldType = new JSONObject();
//                 if (value instanceof String) {
//                     fieldType.put("stringValue", value);
//                 } else if (value instanceof Integer) {
//                     fieldType.put("integerValue", value);
//                 } else if (value instanceof Boolean) {
//                     fieldType.put("booleanValue", value);
//                 } else if (value instanceof Double) {
//                     fieldType.put("doubleValue", value);
//                 }
//                 // Add more types as needed (e.g., arrayValue, mapValue, geoPointValue, timestampValue)
//                 // For null values, use `nullValue`
//                 else if (value == null) {
//                     fieldType.put("nullValue", JSONObject.NULL); // Represents Firestore's NULL_VALUE
//                 }
//                 // Default to stringValue for unknown types, or throw an error
//                 else {
//                     System.err.println("Unsupported field type for " + fieldName + ": " + value.getClass().getName());
//                     fieldType.put("stringValue", value.toString()); // Fallback
//                 }
//                 fields.put(fieldName, fieldType);
//             }
//             jsonPayload.put("fields", fields);

//             try (OutputStream os = conn.getOutputStream()) {
//                 os.write(jsonPayload.toString().getBytes(StandardCharsets.UTF_8));
//             }

//             int responseCode = conn.getResponseCode();
//             if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK for successful PATCH
//                 System.out.println("Firestore document updated successfully: " + collection + "/" + documentId);
//                 return true;
//             } else {
//                 System.err.println("Failed to update Firestore document. Response Code: " + responseCode);
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
//                     StringBuilder errorResponse = new StringBuilder();
//                     String line;
//                     while((line = br.readLine()) != null) {
//                         errorResponse.append(line);
//                     }
//                     System.err.println("Firestore Update Error: " + errorResponse.toString());
//                 }
//                 return false;
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//             System.err.println("Exception during Firestore document update: " + e.getMessage());
//             return false;
//         }
//     }

    
//     public static Map<String, Object> fetchProfileData(String collection, String documentId) {
//         try {
//             String endpoint = String.format(
//                 "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s",
//                 PROJECT_ID, collection, documentId, API_KEY
//             );

//             URL url = new URL(endpoint);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("GET");
//             conn.setRequestProperty("Content-Type", "application/json");

//             int responseCode = conn.getResponseCode();
//             if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
//                     StringBuilder response = new StringBuilder();
//                     String line;
//                     while ((line = br.readLine()) != null) {
//                         response.append(line);
//                     }
//                     JSONObject jsonResponse = new JSONObject(response.toString());
                    
//                     // Parse the "fields" object
//                     JSONObject fields = jsonResponse.optJSONObject("fields");
//                     Map<String, Object> data = new HashMap<>();

//                     if (fields != null) {
//                         for (String key : fields.keySet()) {
//                             JSONObject fieldType = fields.getJSONObject(key);
//                             if (fieldType.has("stringValue")) {
//                                 data.put(key, fieldType.getString("stringValue"));
//                             } else if (fieldType.has("integerValue")) {
//                                 data.put(key, fieldType.getLong("integerValue")); // Use getLong for integerValue
//                             } else if (fieldType.has("booleanValue")) {
//                                 data.put(key, fieldType.getBoolean("booleanValue"));
//                             } else if (fieldType.has("doubleValue")) {
//                                 data.put(key, fieldType.getDouble("doubleValue"));
//                             }
//                             // Add other types as needed
//                         }
//                     }
//                     System.out.println("Firestore document fetched successfully: " + collection + "/" + documentId);
//                     return data;
//                 }
//             } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) { // 404 Not Found
//                 System.out.println("Document not found for: " + collection + "/" + documentId);
//                 return null;
//             } else {
//                 System.err.println("Failed to fetch Firestore document. Response Code: " + responseCode);
//                 try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
//                     StringBuilder errorResponse = new StringBuilder();
//                     String line;
//                     while((line = br.readLine()) != null) {
//                         errorResponse.append(line);
//                     }
//                     System.err.println("Firestore Fetch Error: " + errorResponse.toString());
//                 }
//                 return null;
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.err.println("Exception during Firestore document fetch: " + e.getMessage());
//             return null;
//         }
//     }
// }



package com.tirthongo.dao;

import org.json.JSONObject;

import com.tirthongo.model.AdminProfileModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProfileDao {

    private static final String PROJECT_ID = "javafx-dce2b";
    private static final String API_KEY = "AIzaSyDnDmDVPkBZdjn4qBFCv5ClxENY1NMLT1M";

    public static boolean updateProfileData(String collection, String documentId, Map<String, Object> fieldsToUpdate) {
        try {
            String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s&updateMask.fieldPaths=",
                PROJECT_ID, collection, documentId, API_KEY
            );

            // Construct updateMask for the PATCH request
            StringBuilder updateMask = new StringBuilder();
            for (String fieldName : fieldsToUpdate.keySet()) {
                if (updateMask.length() > 0) {
                    updateMask.append("&updateMask.fieldPaths=");
                }
                updateMask.append(fieldName);
            }
            endpoint += updateMask.toString();

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST"); // Use PATCH for partial updates
            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Build the JSON payload for fields
            JSONObject jsonPayload = new JSONObject();
            JSONObject fields = new JSONObject();
            for (Map.Entry<String, Object> entry : fieldsToUpdate.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                JSONObject fieldType = new JSONObject();
                if (value instanceof String) {
                    fieldType.put("stringValue", value);
                } else if (value instanceof Integer) {
                    fieldType.put("integerValue", value);
                } else if (value instanceof Boolean) {
                    fieldType.put("booleanValue", value);
                } else if (value instanceof Double) {
                    fieldType.put("doubleValue", value);
                }
                // Add more types as needed (e.g., arrayValue, mapValue, geoPointValue, timestampValue)
                // For null values, use `nullValue`
                else if (value == null) {
                    fieldType.put("nullValue", JSONObject.NULL); // Represents Firestore's NULL_VALUE
                }
                // Default to stringValue for unknown types, or throw an error
                else {
                    System.err.println("Unsupported field type for " + fieldName + ": " + value.getClass().getName());
                    fieldType.put("stringValue", value.toString()); // Fallback
                }
                fields.put(fieldName, fieldType);
            }
            jsonPayload.put("fields", fields);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.toString().getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK for successful PATCH
                System.out.println("Firestore document updated successfully: " + collection + "/" + documentId);
                return true;
            } else {
                System.err.println("Failed to update Firestore document. Response Code: " + responseCode);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.err.println("Firestore Update Error: " + errorResponse.toString());
                }
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception during Firestore document update: " + e.getMessage());
            return false;
        }
    }

    
    public static Map<String, Object> fetchProfileData(String collection, String documentId) {
        try {
            String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/%s/%s?key=%s",
                PROJECT_ID, collection, documentId, API_KEY
            );

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    
                    // Parse the "fields" object
                    JSONObject fields = jsonResponse.optJSONObject("fields");
                    Map<String, Object> data = new HashMap<>();

                    if (fields != null) {
                        for (String key : fields.keySet()) {
                            JSONObject fieldType = fields.getJSONObject(key);
                            if (fieldType.has("stringValue")) {
                                data.put(key, fieldType.getString("stringValue"));
                            } else if (fieldType.has("integerValue")) {
                                data.put(key, fieldType.getLong("integerValue")); // Use getLong for integerValue
                            } else if (fieldType.has("booleanValue")) {
                                data.put(key, fieldType.getBoolean("booleanValue"));
                            } else if (fieldType.has("doubleValue")) {
                                data.put(key, fieldType.getDouble("doubleValue"));
                            }
                            // Add other types as needed
                        }
                    }
                    System.out.println("Firestore document fetched successfully: " + collection + "/" + documentId);
                    return data;
                }
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) { // 404 Not Found
                System.out.println("Document not found for: " + collection + "/" + documentId);
                return null;
            } else {
                System.err.println("Failed to fetch Firestore document. Response Code: " + responseCode);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.err.println("Firestore Fetch Error: " + errorResponse.toString());
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception during Firestore document fetch: " + e.getMessage());
            return null;
        }
    }



public static AdminProfileModel getAdminProfile(String adminId) {
        // Calls the general fetchProfileData method to get the raw Map data
        Map<String, Object> adminDataMap = fetchProfileData("admins", adminId);
        if (adminDataMap == null) {
            System.out.println("Admin profile data map is null for UID: " + adminId); // Debugging
            return null; // Profile not found or error occurred in fetchProfileData
        }
        
        // --- Map the raw Map<String, Object> data to your AdminProfileModel POJO ---
        AdminProfileModel profile = new AdminProfileModel();
        profile.setDocumentId(adminId); // Set the UID as documentId (from Firebase Auth)

        // Use getOrDefault to safely retrieve values and cast them to String
        profile.setFirstName((String) adminDataMap.getOrDefault("firstName", null));
        profile.setLastName((String) adminDataMap.getOrDefault("lastName", null));
        profile.setGender((String) adminDataMap.getOrDefault("gender", null));
        profile.setDob((String) adminDataMap.getOrDefault("dob", null)); // DOB is stored as String
        profile.setEmail((String) adminDataMap.getOrDefault("email", null));
        profile.setMobile((String) adminDataMap.getOrDefault("mobile", null)); // Map to 'mobile' field
        profile.setGstNumber((String) adminDataMap.getOrDefault("gstNumber", null));
        profile.setAddress((String) adminDataMap.getOrDefault("address", null));
        profile.setState((String) adminDataMap.getOrDefault("state", null));
        profile.setPincode((String) adminDataMap.getOrDefault("pincode", null));
        profile.setOfficeLocation((String) adminDataMap.getOrDefault("officeLocation", null));
        profile.setProfileImageUrl((String) adminDataMap.getOrDefault("profileImageUrl", null));
        
        System.out.println("Mapped AdminProfileModel for UID: " + adminId + " Email: " + profile.getEmail()); // Debugging
        return profile;
    }
}