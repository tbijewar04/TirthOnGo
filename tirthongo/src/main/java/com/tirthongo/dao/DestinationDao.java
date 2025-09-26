package com.tirthongo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.tirthongo.model.DestinationModel;

public class DestinationDao
{
    public static void addDesitnationToFirestore(DestinationModel model){
        ApiFuture<DocumentReference> docRef = FirestoreClient.getFirestore().collection("destniation_details").add(model);
        try {
            System.out.println(docRef.get().getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String uploadImage(File file) throws FileNotFoundException{
        String BUCKET = "javafx-dce2b.appspot.com";
        String imageName = file.getName();
        String encodedFileName = imageName.replace(" ", "%20");
        String remoteFilePath = "Destination_Images/"+encodedFileName;
        FileInputStream fis = new FileInputStream(file);

        StorageClient.getInstance().bucket().create(encodedFileName, fis, "application/json");

        return "https://firebasestorage.googleapis.com/v0/b"+BUCKET+"/o/"+remoteFilePath;
    }
        

    

    }
