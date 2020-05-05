package datalayer;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FireStoreDB {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FireStoreDB run = new FireStoreDB();
        Firestore db = run.initializeConnection();

        run.getAlleBrugerProjekter(db,"Mark");
    }
    public Firestore initializeConnection() throws IOException {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //String path = System.getProperty("user.dir")+"/src/main/java/ServiceAccountKey.json"; //sets key to current dir

        FileInputStream serviceAccount = new FileInputStream("./ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://backendaflevering.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        System.out.println("Collections: "+db.listCollections());

        return db;
    }

    public boolean addProjekt(Projekt projekt,Firestore db) throws ExecutionException, InterruptedException {
        try{
        ApiFuture<WriteResult> future = db.collection("Projects").document(projekt.getProjektnavn()).set(projekt);
        System.out.println("Databasen blev opdateret : "+ future.get().getUpdateTime());
        return true;
        }
        catch (Exception e){
            System.out.println("Kunne ikke oprette projekt");
            return false;
        }
    }

    public void updateProjekt(Firestore db,Projekt projekt) throws ExecutionException, InterruptedException {
        Map<String,Object> docData = new HashMap<>();
        DocumentReference ProjektRef = db.collection("Projects").document("projektID");

        docData.put("projektnavn",projekt.getProjektnavn()); // user.getFirstName
        docData.put("projekttid",projekt.getProjekttid()); // user.getLastName
        //docData.put("medlemmer",projekt.getMedlemmer()); // user.getLastName
    }

    public Projekt getBrugerProjekt(Firestore db, String projektID) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("Projects").document(projektID);
        // henter dokument
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot dokument = future.get();
        if (dokument.exists()) {
            Projekt projekt = dokument.toObject(Projekt.class);
            System.out.println("Dokument data: " + dokument.getData());
            return projekt;
        } else {
            System.out.println("Kunne ikke finde dokument!");
            return null;
        }
    }

    public ArrayList<Projekt> getAlleBrugerProjekter(Firestore db, String username) throws ExecutionException, InterruptedException {
        ArrayList<Projekt> projekter = new ArrayList<>();

        CollectionReference projectsref = db.collection("Projects");
        // check medlemmer . equals (username)

        Query searchQuery = projectsref.whereArrayContains("medlemmer",username);

        ApiFuture<QuerySnapshot> querySnapshot = searchQuery.get();
        System.out.println("Downloader projekt dokumenter for bruger "+username+": ");
        for (DocumentSnapshot dokument : querySnapshot.get().getDocuments()){
            Projekt projekt = dokument.toObject(Projekt.class);
            projekter.add(projekt);
        }
        return projekter;
    }

    public boolean addStuderende(Studerende studerende, Firestore db) throws ExecutionException, InterruptedException{
        try{
        ApiFuture<WriteResult> future = db.collection("Studerende").document(studerende.getBrugernavn()).set(studerende);
        System.out.println("Databasen blev opdateret : "+ future.get().getUpdateTime());
            return true;
        }
        catch (Exception e){
            System.out.println("Kunne ikke oprette studerende!");
            return false;
        }
    }

    public void updateStuderende(Studerende studerende, Firestore db) throws ExecutionException, InterruptedException{

    }

    public Studerende getStuderende(String brugernavn,Firestore db)throws ExecutionException, InterruptedException{
        DocumentReference docRef = db.collection("Studerende").document(brugernavn);
        // henter dokument
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot dokument = future.get();
        if (dokument.exists()) {
            System.out.println("Dokument data: " + dokument.getData());
            Studerende studerende = dokument.toObject(Studerende.class);
            return studerende;
        } else {
            System.out.println("Kunne ikke finde dokument!");
            return null;
        }
    }
}
