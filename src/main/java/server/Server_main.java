package server;

import com.google.cloud.firestore.Firestore;
import controller.Rest_controller;
import datalayer.FireStoreDB;

public class Server_main {
   static FireStoreDB run;
   static Firestore db;

    public static void main(String[] args) throws Exception {
        run = new FireStoreDB();
        db = run.initializeConnection();

        Rest_controller server = new Rest_controller();
        try{
            server.start();
        } catch(Exception e){
            System.out.println("Exception triggered: "+e);
            System.out.println("Stopping server.... cya :D");
            server.stop();
        }

    }
}
