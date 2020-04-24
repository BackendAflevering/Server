package datalayer;

import java.util.Arrays;
import java.util.List;

public class Projekt {
    private static long serialVersionUID = 54321;

    private String projektnavn;
    private long projekttid;
    //private List<String> medlemmer;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Projekt.serialVersionUID = serialVersionUID;
    }

    public String getProjektnavn() {
        return projektnavn;
    }

    public void setProjektnavn(String projektnavn) {
        this.projektnavn = projektnavn;
    }



    public long getProjekttid() {
        return projekttid;
    }

    public void setProjekttid(long projekttid) {
        this.projekttid = projekttid;
    }

    //public List<String> getMedlemmer() {
       // return medlemmer;
    //}

    //public void setMedlemmer(List<String> medlemmer) {
    // this.medlemmer = medlemmer;
    // }

    public Projekt  (String projektnavn, long projekttid){
        this.projektnavn = projektnavn;
        this.projekttid = projekttid;
        //this.medlemmer = medlemmer;
    }

    @Override
    public String toString() {
        return "Projekt{" +
                "navn='" + projektnavn + '\'' +
                ", projekttid=" + projekttid +
                // ", medlemmer=" + medlemmer.toString() +
                '}';
    }
}
