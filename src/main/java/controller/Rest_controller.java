package controller;

import datalayer.Studerende;
import io.javalin.Javalin;
import io.javalin.http.Context;
import logik.Login;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;


public class Rest_controller {
   public static Javalin server;
   public static Login login;
   public static Studerende studerende;

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
       server.post("/nystuderende",ctx -> studerende(ctx));
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

    private String studerende(@NotNull Context ctx) {
       String brugernavn = ctx.queryParam("brugernavn");
       String gruppe = ctx.queryParam("gruppe");
       String mail = ctx.queryParam("mail");
       long ugetid = 0;
       long projekttid = 0;
       boolean gruppeleder = ctx.equals(null);
       studerende = new Studerende(brugernavn, gruppe, ugetid, projekttid, mail, gruppeleder);
       return studerende.toString();
    }
}
