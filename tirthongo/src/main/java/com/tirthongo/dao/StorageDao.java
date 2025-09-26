// package com.tirthongo.dao;


// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.cloud.storage.Blob;
// import com.google.cloud.storage.BlobId;
// import com.google.cloud.storage.BlobInfo;
// import com.google.cloud.storage.Storage;
// import com.google.cloud.storage.Acl;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.cloud.StorageClient;
// import com.google.cloud.WriteChannel; // NEW IMPORT: For writing to Storage

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.nio.channels.Channels; // NEW IMPORT: To get an OutputStream from WriteChannel
// import java.nio.file.Files;
// import java.io.OutputStream; // NEW IMPORT: For writing to the channel

// public class StorageDao {

//     public static void initializeFirebaseAdminSDKForStorage() {
//         if (!FirebaseApp.getApps().isEmpty()) {
//             System.out.println("Firebase Admin SDK already initialized. Skipping Storage-specific initialization.");
//             return;
//         }

//         try {
//             InputStream serviceAccount = StorageDao.class.getResourceAsStream("/Assets/Images/tirthOnGo_sdk.json");
            
//             if (serviceAccount == null) {
//                 System.err.println("CRITICAL ERROR: Firebase service account key file 'tirthOnGo_sdk.json' not found.");
//                 System.err.println("Ensure it's in your project's 'src/main/resources/Assets/Images/' directory.");
//                 return;
//             }

//             String bucketName = "javafx-dce2b.appspot.com"; // VERIFY/REPLACE THIS WITH YOUR ACTUAL BUCKET ID!

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .setStorageBucket(bucketName)
//                     .build();

//             FirebaseApp.initializeApp(options);
//             System.out.println("Firebase Admin SDK initialized successfully for Storage.");

//         } catch (IOException e) {
//             System.err.println("Error initializing Firebase Admin SDK for Storage (IO Exception): " + e.getMessage());
//             e.printStackTrace();
//         } catch (Exception e) {
//             System.err.println("An unexpected error occurred during Firebase Admin SDK initialization for Storage: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }

//     public static String uploadProfileImage(String userId, File imageFile) {
//         if (userId == null || userId.isEmpty()) {
//             System.err.println("Error uploading image: userId is null or empty. Cannot determine storage path.");
//             return null;
//         }
//         if (imageFile == null || !imageFile.exists()) {
//             System.err.println("Error uploading image: Image file is null or does not exist on disk.");
//             return null;
//         }

//         try {
//             FirebaseApp app = FirebaseApp.getInstance();
//             if (app.getOptions().getStorageBucket() == null) {
//                 System.err.println("Firebase Admin SDK was not initialized with a Storage Bucket. Cannot upload image.");
//                 return null;
//             }

//             StorageClient storageClient = StorageClient.getInstance(app);
//             Storage storage = storageClient.bucket().getStorage();

//             String objectName = "profile_images/" + userId + "/profile_picture.jpg";

//             String contentType = Files.probeContentType(imageFile.toPath());
//             if (contentType == null) {
//                 if (imageFile.getName().toLowerCase().endsWith(".png")) contentType = "image/png";
//                 else if (imageFile.getName().toLowerCase().endsWith(".jpg") || imageFile.getName().toLowerCase().endsWith(".jpeg")) contentType = "image/jpeg";
//                 else if (imageFile.getName().toLowerCase().endsWith(".gif")) contentType = "image/gif";
//                 else contentType = "application/octet-stream";
//             }

//             BlobId blobId = BlobId.of(storageClient.bucket().getName(), objectName);
//             BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();

//             // --- CRITICAL FIX: Use Storage.writer() for non-deprecated upload ---
//             try (WriteChannel writer = storage.writer(blobInfo);
//                  InputStream fileInputStream = new FileInputStream(imageFile)) {
                
//                 OutputStream outputStream = Channels.newOutputStream(writer);
//                 byte[] buffer = new byte[1024]; // Small buffer for chunked writing
//                 int bytesRead;
//                 while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                     outputStream.write(buffer, 0, bytesRead);
//                 }
//             } // writer and fileInputStream are auto-closed here

//             // Get the Blob object after creation/upload
//             Blob blob = storage.get(blobId); // Retrieve the Blob object using its ID

//             // Set public read access using ACLs (as previously fixed)
//             blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER)); 
            
//             String downloadUrl = String.format("https://storage.googleapis.com/%s/%s", blob.getBucket(), blob.getName());

//             System.out.println("Image uploaded to Firebase Storage successfully: " + downloadUrl);
//             return downloadUrl;

//         } catch (IOException e) {
//             System.err.println("IO Error during image upload: " + e.getMessage());
//             e.printStackTrace();
//             return null;
//         } catch (Exception e) {
//             System.err.println("An unexpected error occurred during image upload to Firebase Storage: " + e.getMessage());
//             e.printStackTrace();
//             return null;
//         }
//     }
// }






package com.tirthongo.dao;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Acl;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.WriteChannel; // NEW IMPORT: For writing to Storage

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels; // NEW IMPORT: To get an OutputStream from WriteChannel
import java.nio.file.Files;
import java.io.OutputStream; // NEW IMPORT: For writing to the channel

public class StorageDao {

    public static void initializeFirebaseAdminSDKForStorage() {
        if (!FirebaseApp.getApps().isEmpty()) {
            System.out.println("Firebase Admin SDK already initialized. Skipping Storage-specific initialization.");
            return;
        }

        try {
            InputStream serviceAccount = StorageDao.class.getResourceAsStream("/Assets/Images/tirthOnGo_sdk.json");
            
            if (serviceAccount == null) {
                System.err.println("CRITICAL ERROR: Firebase service account key file 'tirthOnGo_sdk.json' not found.");
                System.err.println("Ensure it's in your project's 'src/main/resources/Assets/Images/' directory.");
                return;
            }

            String bucketName = "javafx-dce2b.appspot.com"; // VERIFY/REPLACE THIS WITH YOUR ACTUAL BUCKET ID!

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(bucketName)
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase Admin SDK initialized successfully for Storage.");

        } catch (IOException e) {
            System.err.println("Error initializing Firebase Admin SDK for Storage (IO Exception): " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during Firebase Admin SDK initialization for Storage: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String uploadProfileImage(String userId, File imageFile) {
        if (userId == null || userId.isEmpty()) {
            System.err.println("Error uploading image: userId is null or empty. Cannot determine storage path.");
            return null;
        }
        if (imageFile == null || !imageFile.exists()) {
            System.err.println("Error uploading image: Image file is null or does not exist on disk.");
            return null;
        }

        try {
            FirebaseApp app = FirebaseApp.getInstance();
            if (app.getOptions().getStorageBucket() == null) {
                System.err.println("Firebase Admin SDK was not initialized with a Storage Bucket. Cannot upload image.");
                return null;
            }

            StorageClient storageClient = StorageClient.getInstance(app);
            Storage storage = storageClient.bucket().getStorage();

            String objectName = "profile_images/" + userId + "/profile_picture.jpg";

            String contentType = Files.probeContentType(imageFile.toPath());
            if (contentType == null) {
                if (imageFile.getName().toLowerCase().endsWith(".png")) contentType = "image/png";
                else if (imageFile.getName().toLowerCase().endsWith(".jpg") || imageFile.getName().toLowerCase().endsWith(".jpeg")) contentType = "image/jpeg";
                else if (imageFile.getName().toLowerCase().endsWith(".gif")) contentType = "image/gif";
                else contentType = "application/octet-stream";
            }

            BlobId blobId = BlobId.of(storageClient.bucket().getName(), objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();

            // --- CRITICAL FIX: Use Storage.writer() for non-deprecated upload ---
            try (WriteChannel writer = storage.writer(blobInfo);
                 InputStream fileInputStream = new FileInputStream(imageFile)) {
                
                OutputStream outputStream = Channels.newOutputStream(writer);
                byte[] buffer = new byte[1024]; // Small buffer for chunked writing
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } // writer and fileInputStream are auto-closed here

            // Get the Blob object after creation/upload
            Blob blob = storage.get(blobId); // Retrieve the Blob object using its ID

            // Set public read access using ACLs (as previously fixed)
            blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER)); 
            
            String downloadUrl = String.format("https://storage.googleapis.com/%s/%s", blob.getBucket(), blob.getName());

            System.out.println("Image uploaded to Firebase Storage successfully: " + downloadUrl);
            return downloadUrl;

        } catch (IOException e) {
            System.err.println("IO Error during image upload: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during image upload to Firebase Storage: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}