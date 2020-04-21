package datalayer;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FireStoreDB {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FireStoreDB run = new FireStoreDB();
        Firestore db;
        db = run.initializeConnection();
        run.get(db,"1");
    }

    public Firestore initializeConnection() throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String path = System.getProperty("user.dir")+"/src/main/java/ServiceAccountKey.json"; //sets key to current dir

        FileInputStream serviceAccount = new FileInputStream(path);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://backendaflevering.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        System.out.println("Collections: "+db.listCollections());

        return db;
    }

    public void addProjektToDB(Projekt projekt,Firestore db) throws ExecutionException, InterruptedException {
        Map<String,Object> docData = new HashMap<>();

        docData.put("projektID",projekt.getProjektID()); // user.getID
        docData.put("projektnavn",projekt.getProjektnavn()); // user.getFirstName
        docData.put("projekttid",projekt.getProjekttid()); // user.getLastName
        docData.put("medlemmer",projekt.getMedlemmer()); // user.getLastName


        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("Projects").document("xEtPzeySvbMedc1q681I").set(docData);

        System.out.println("Database was updated at time : "+ future.get().getUpdateTime());
    }

    public void update(Firestore db,Projekt projekt) throws ExecutionException, InterruptedException {
        Map<String,Object> docData = new HashMap<>();
        DocumentReference ProjektRef = db.collection("Projects").document("projektID");

        docData.put("projektID",projekt.getProjektID()); // user.getID
        docData.put("projektnavn",projekt.getProjektnavn()); // user.getFirstName
        docData.put("projekttid",projekt.getProjekttid()); // user.getLastName
        docData.put("medlemmer",projekt.getMedlemmer()); // user.getLastName

    }

    public DocumentSnapshot get(Firestore db, String projektID) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("Projects").document(projektID);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            return document;
        } else {
            System.out.println("No such document!");
        }
        return null;
    }


}
