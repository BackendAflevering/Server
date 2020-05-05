package controller;

import com.google.api.client.json.Json;
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
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Rest_controller {
   public static Javalin server;
   public static Login login;
    static FireStoreDB run;
    static Firestore db;
    String url = "ec2-13-48-130-164.eu-north-1.compute.amazonaws.com";

   public void stop(){
       server.stop();
       server = null;
   }

   public void start() throws Exception{
       run = new FireStoreDB();
       db = run.initializeConnection();
       login = new Login();
       if (server!=null) return;

       server = Javalin.create().start(8080);
       server.exception(Exception.class, (e,ctx)-> {e.printStackTrace();});
       //TODO: lav endpoints (GET og POST)
       server.post("/login",ctx -> login(ctx));
       server.get("/getStuderende",ctx -> getStuderende(ctx));
       server.get("/getBrugerProjekt",ctx -> getBrugerProjekt(ctx));
       server.get("/getAlleBrugerProjekter",ctx-> getAlleBrugerProjekter(ctx));
       server.post("/nyStuderende",ctx -> nyStuderende(ctx));
       server.post("/nytProjekt",ctx -> nytprojekt(ctx));
   }

   private static void login(@NotNull Context ctx) throws ExecutionException, InterruptedException {

       String brugernavn = ctx.queryParam("brugernavn");
       String kodeord = ctx.queryParam("kodeord");

            System.out.println("Brugernavn: "+brugernavn+" og kodeord: "+kodeord);
               try {
                   if (login.tjekLogin(brugernavn,kodeord)){
                       System.out.println("Login: Succes");
                       ctx.status(200).result("Succes");
                   }
                   else {
                       System.out.println("Login: Failed");
                       ctx.status(401).result("Unauthorized");
                   }
               }catch (MalformedURLException e){
                   e.printStackTrace();
                   ctx.status(500).result("Server Error");
               }

    }

    private static void nyStuderende(@NotNull Context ctx) throws ExecutionException, InterruptedException {
       Studerende studerende = ctx.bodyAsClass(Studerende.class);
        System.out.println("Skal til at oprette en ny Studerende: ");
        System.out.println(ctx.body());
       if (run.addStuderende(studerende,db)){
           ctx.json(studerende);
       } else {ctx.status(500).result("Server Error");}

    }

    private static void getBrugerProjekt(@NotNull Context ctx) throws IOException, ExecutionException, InterruptedException {
        System.out.println("Getting projekt");
        String id = ctx.queryParam("projektID");

        Projekt projekt = run.getBrugerProjekt(db,id);
        if (projekt != null){
        ctx.json(projekt);
        }
        else{ctx.status(404).result("Not Found");}
    }

    private static void getAlleBrugerProjekter(@NotNull Context ctx)throws IOException, ExecutionException, InterruptedException{
       String username = ctx.queryParam("brugernavn");
       ArrayList<Projekt> brugerProjekter;
       brugerProjekter = run.getAlleBrugerProjekter(db,username);
       if(!brugerProjekter.isEmpty()){
        for(int i = 0; i < brugerProjekter.size(); i++){
            System.out.println(brugerProjekter.get(i).toString());
        }
           ctx.json(brugerProjekter);}
       else {
           System.out.println("404: Not Found");
           ctx.status(404).result("Not Found");
       }
    }

    private static void getStuderende(@NotNull Context ctx) throws ExecutionException, InterruptedException {
        String brugernavn = ctx.queryParam("brugernavn");
        Studerende studerende = run.getStuderende(brugernavn,db);
        if (studerende!=null){
            ctx.json(studerende);
        }
        else{ ctx.status(404).result("Not Found");}
    }

    private static void nytprojekt(@NotNull Context ctx) throws ExecutionException, InterruptedException {
       Projekt projekt = ctx.bodyAsClass(Projekt.class);
        System.out.println("Skal til at oprette et nyt projekt");
        System.out.println(ctx.body());
        if(run.addProjekt(projekt,db)){
        ctx.json(projekt);}
        else{ctx.status(500).result("Server Error");}
    }
}
