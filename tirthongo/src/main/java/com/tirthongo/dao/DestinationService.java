package com.tirthongo.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.tirthongo.model.DestinationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DestinationService {

    public static List<DestinationModel> fetchAllDestinations() {
        List<DestinationModel> destinationList = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();

        try {
            ApiFuture<QuerySnapshot> future = db.collection("destniation_details").get();

            for(int i = 0;i<future.get().getDocuments().size();i++){
                // System.out.println(future.get().getDocuments().get(i).getId());
               destinationList.add(future.get().getDocuments().get(i).toObject(DestinationModel.class));

            }

            // for (QueryDocumentSnapshot doc : documents) {
            //     DestinationModel model = doc.toObject(DestinationModel.class);
            //     model.setAdminDocId(doc.getId());
            //     destinationList.add(model);
            // }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(destinationList);

        return destinationList;
    }
}

