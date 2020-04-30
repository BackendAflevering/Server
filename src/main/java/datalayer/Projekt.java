package datalayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Projekt {
    private static long serialVersionUID = 54321;

    private String projektnavn;
    private long projekttid;
    private ArrayList<String> medlemmer;

    public Projekt(){
    }
    public Projekt  (String projektnavn, long projekttid, ArrayList<String> medlemmer){
        this.projektnavn = projektnavn;
        this.projekttid = projekttid;
        this.medlemmer = medlemmer;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Projekt.serialVersionUID = serialVersionUID;
    }

    public String getProjektnavn() {
        return projektnavn;
    }

    public long getProjekttid() {
        return projekttid;
    }

    public ArrayList<String> getMedlemmer() {
        return medlemmer;
    }

    @Override
    public String toString() {
        return "Projekt{" +
                "navn='" + projektnavn + '\'' +
                ", projekttid=" + projekttid +
                ", medlemmer=" + medlemmer.toString() +
                '}';
    }
}
