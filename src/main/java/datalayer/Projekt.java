package datalayer;

import java.util.Arrays;

public class Projekt {
    private static long serialVersionUID = 54321;

    private String projektnavn;
    private long ugetid;
    private long projekttid;
    private Studerende[] medlemmer;

    public Projekt (String projektnavn, long ugetid, long projekttid, Studerende[] medlemmer){
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
