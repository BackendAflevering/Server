package datalayer;

import java.util.Arrays;

public class Projekt {
    private static long serialVersionUID = 54321;

    private int projektID;
    private String projektnavn;
    private long ugetid;
    private long projekttid;
    private Studerende[] medlemmer;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Projekt.serialVersionUID = serialVersionUID;
    }

    public int getProjektID() {
        return projektID;
    }

    public void setProjektID(int projektID) {
        this.projektID = projektID;
    }

    public String getProjektnavn() {
        return projektnavn;
    }

    public void setProjektnavn(String projektnavn) {
        this.projektnavn = projektnavn;
    }

    public long getUgetid() {
        return ugetid;
    }

    public void setUgetid(long ugetid) {
        this.ugetid = ugetid;
    }

    public long getProjekttid() {
        return projekttid;
    }

    public void setProjekttid(long projekttid) {
        this.projekttid = projekttid;
    }

    public Studerende[] getMedlemmer() {
        return medlemmer;
    }

    public void setMedlemmer(Studerende[] medlemmer) {
        this.medlemmer = medlemmer;
    }

    public Projekt (int projektID, String projektnavn, long ugetid, long projekttid, Studerende[] medlemmer){
        this.projektID = projektID;
        this.projektnavn = projektnavn;
        this.ugetid = ugetid;
        this.projekttid = projekttid;
        this.medlemmer = medlemmer;
    }

    @Override
    public String toString() {
        return "Projekt{" +
                "navn='" + projektnavn + '\'' +
                ", ugetid=" + ugetid +
                ", projekttid=" + projekttid +
                ", medlemmer=" + Arrays.toString(medlemmer) +
                '}';
    }
}
