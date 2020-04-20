import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import datalayer.Firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FireBaseTest {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FireBaseTest run = new FireBaseTest();
        Firestore db;
        db = run.initializeConnection();

        run.writeUserToFireStore(db);
    }

    public Firestore initializeConnection() throws IOException {
        String path = "C:/Users/Mark/IdeaProjects/Server/src/ServiceAccountKey.json";
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

    public void writeUserToFireStore(Firestore db) throws ExecutionException, InterruptedException {
        // Create a Map to store the data we want to set, could be our user object
        Map<String,Object> docData = new HashMap<>();
        docData.put("id",1); // user.getID
        docData.put("name","Mark"); // user.getFirstName
        docData.put("last name","Malloy"); // user.getLastName

        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("users").document("name").set(docData);

        System.out.println("Database was updated at time : "+ future.get().getUpdateTime());
    }

}
