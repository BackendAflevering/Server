package klient;

import datalayer.Projekt;
import datalayer.Studerende;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;

public class Klient_main {
    static String url = "http://ec2-13-48-130-164.eu-north-1.compute.amazonaws.com:80";
    static String localurl = "http://192.168.1.1:8080/";
    static Scanner input = new Scanner(System.in);
    static Client client;
    static Response response;
    static String brugernavn;

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.CONNECT_TIMEOUT,5000);
        config.property(ClientProperties.READ_TIMEOUT,5000);
        client = ClientBuilder.newClient(config);
        WebTarget target = client.target(localurl);
        //login(target);
        getProjekter(brugernavn, target);
    }

    //Login metode. Metoden tager imod destinations url'en.
    public static boolean login(WebTarget target){
        Boolean status = false;
        System.out.println("Venligst indtast brugernavn: ");
        brugernavn = input.nextLine();
        System.out.println("Venligst indtast kodeord: ");
        String kodeord = input.nextLine();

        //Vi tilføjer login til stien så vi kan kalde en specific resoruce.
        WebTarget login = target.path("login");
        Invocation.Builder builder = login.request(MediaType.TEXT_PLAIN_TYPE);
        Studerende studerende = new Studerende(brugernavn,kodeord);
        response = builder.post(Entity.entity(studerende, MediaType.APPLICATION_JSON_TYPE));

        return status;
    }

    public static void getProjekter(String brugernavn, WebTarget target){
        WebTarget projekter = target.path("getAlleBrugerProjekter"+brugernavn);
        Invocation.Builder builder = projekter.request(MediaType.APPLICATION_JSON_TYPE);
        response = builder.get();
    }

}
