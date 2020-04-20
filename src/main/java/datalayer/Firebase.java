package datalayer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;

public class Firebase {
    FirebaseOptions options;

    {
        try {
            options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .setDatabaseUrl("https://backend-final-bc881.firebaseio.com/")
                        .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
