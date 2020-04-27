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

        List<String> medlemmer = new ArrayList<String>();
        medlemmer.add("jenje");
        medlemmer.add("Mark");
        
        Projekt projekt = new Projekt("fedt",0);
        //run.addProjekt(projekt,db);
        run.getProjekter(db,"Mark");
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

    public void addProjekt(Projekt projekt,Firestore db) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("Projects").document(projekt.getProjektnavn()).set(projekt);

        System.out.println("Database was updated at time : "+ future.get().getUpdateTime());
    }

    public void updateProjekt(Firestore db,Projekt projekt) throws ExecutionException, InterruptedException {
        Map<String,Object> docData = new HashMap<>();
        DocumentReference ProjektRef = db.collection("Projects").document("projektID");

        docData.put("projektnavn",projekt.getProjektnavn()); // user.getFirstName
        docData.put("projekttid",projekt.getProjekttid()); // user.getLastName
        //docData.put("medlemmer",projekt.getMedlemmer()); // user.getLastName

    }

    public DocumentSnapshot getProjekt(Firestore db, String projektID) throws ExecutionException, InterruptedException {
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

    public ArrayList<String> getProjekter(Firestore db, String username) throws ExecutionException, InterruptedException {
        ArrayList<String> documents = new ArrayList<>();

        CollectionReference projectsref = db.collection("Projects");
        // check medlemmer . equals (username)

        Query searchQuery = projectsref.whereArrayContains("medlemmer",username);

        ApiFuture<QuerySnapshot> querySnapshot = searchQuery.get();
        System.out.println("Printing project documents for user "+username+": ");
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()){
            System.out.println(document.getData().toString());
            documents.add(document.getData().toString());
        }
        return documents;
    }

    public void addStuderende(Studerende studerende, Firestore db) throws ExecutionException, InterruptedException{

        ApiFuture<WriteResult> future = db.collection("Studerende").document(studerende.getBrugernavn()).set(studerende);

        System.out.println("Database was updated at time : "+ future.get().getUpdateTime());
    }
    public void updateStuderende(Studerende studerende, Firestore db) throws ExecutionException, InterruptedException{

    }
}
