package controller;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import datalayer.FireStoreDB;
import datalayer.Projekt;
import datalayer.Studerende;
import io.javalin.Javalin;
import io.javalin.http.Context;
import logik.Login;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Rest_controller {
   public static Javalin server;
   public static Login login;
    static FireStoreDB run;
    static Firestore db;

    public static void main(String[] args) throws Exception {
        run = new FireStoreDB();
        db = run.initializeConnection();

        Rest_controller server = new Rest_controller();
        server.start();

    }

   public void stop(){
       server.stop();
       server = null;
   }

   public void start() throws Exception{
       if (server!=null) return;

       server = Javalin.create().start(8090);
       server.exception(Exception.class, (e,ctx)-> {e.printStackTrace();});
       //TODO: lav endpoints (GET og POST)
       server.get("/login",ctx -> login(ctx));
       server.get("/studerende",ctx -> getStuderende(ctx));
       server.get("/getProjekt",ctx -> getProjekt(ctx));
       server.post("/nystuderende",ctx -> nyStuderende(ctx));
       server.post("/nytprojekt",ctx -> projekt(ctx));

   }

   private static void login(@NotNull Context ctx) throws MalformedURLException {
       String brugernavn = ctx.queryParam("brugernavn");
       String kodeord = ctx.queryParam("kodeord");
       System.out.println("Brugernavn: "+brugernavn+" og kodeord: "+kodeord);
       if (!brugernavn.isEmpty() || !kodeord.isEmpty()) {
           try {
               boolean a = login.tjekLogin(brugernavn,kodeord);
               System.out.println("Login: "+a);
           }catch (Exception e){
               server.stop();
               System.out.println(e);
           }

       } else {
           ctx.contentType("text/html; charset=utf-8").result("<html><body><form method=get>Skriv dit fornavn: <input name=brugernavn type=text></form></html>");
           ctx.contentType("text/html; charset=utf-8").result("<html><body><form method=get>Skriv dit fornavn: <input name=kodeord type=text></form></html>");
       }
    }

    private static void nyStuderende(@NotNull Context ctx) {
       Studerende studerende = ctx.bodyAsClass(Studerende.class);
    }

    private static void getProjekt(@NotNull Context ctx) throws IOException, ExecutionException, InterruptedException {
        String id = ctx.queryParam("projektID");
        System.out.println("Vi finder projektet til ID: "+id);
        DocumentSnapshot doc;

        doc = run.get(db,id);

        ctx.json(doc.getData());

    }

    private static void getStuderende(@NotNull Context ctx){

    }
    private static void projekt(@NotNull Context ctx){

    }
}
