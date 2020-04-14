package controller;

import datalayer.Projekt;
import datalayer.Studerende;
import io.javalin.Javalin;
import io.javalin.http.Context;
import logik.Login;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;


public class Rest_controller {
   public static Javalin server;
   public static Login login;

   public void stop(){
       server.stop();
       server = null;
   }

   public void start() throws Exception{
       if (server!=null) return;

       server = Javalin.create().start(4548);
       server.exception(Exception.class, (e,ctx)-> {e.printStackTrace();});
       //TODO: lav endpoints (GET og POST)
       server.get("/login",ctx -> login(ctx));
       server.get("/studerende",ctx -> getStuderende(ctx));
       server.get("/projekt",ctx -> getProjekt(ctx));
       server.post("/nystuderende",ctx -> nyStuderende(ctx));
       server.post("/nytprojekt",ctx -> projekt(ctx));

   }

   private static void login(@NotNull Context ctx) throws MalformedURLException {
       String brugernavn = ctx.queryParam("brugernavn");
       String kodeord = ctx.queryParam("kodeord");
       if (brugernavn==null || kodeord == null) {
           ctx.contentType("text/html; charset=utf-8").result("<html><body><form method=get>Skriv dit fornavn: <input name=brugernavn type=text></form></html>");
           ctx.contentType("text/html; charset=utf-8").result("<html><body><form method=get>Skriv dit fornavn: <input name=kodeord type=text></form></html>");
       } else {
           login.tjekLogin(brugernavn,kodeord);
       }
    }

    private static void nyStuderende(@NotNull Context ctx) {
       Studerende studerende = ctx.bodyAsClass(Studerende.class);
    }

    private static void projekt(@NotNull Context ctx){
        Projekt projekt = ctx.bodyAsClass(Projekt.class);
    }

    private static void getStuderende(@NotNull Context ctx){

    }
    private static void getProjekt(@NotNull Context ctx){

    }
}
