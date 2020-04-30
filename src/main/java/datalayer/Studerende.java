package datalayer;

import java.util.Arrays;
import java.util.List;

public class Studerende {
    private static final long serialVersionUID = 12345;

    private String brugernavn;
    private String gruppe;
    private long ugetid;
    private boolean gruppeleder;
    private List<String> projekter;

    public Studerende(String brugernavn, String gruppe, long ugetid, boolean gruppeleder, List<String> projekter){
        this.brugernavn = brugernavn;
        this.gruppe = gruppe;
        this.ugetid = ugetid;
        this.gruppeleder = gruppeleder;
        this.projekter = projekter;
    }
    public Studerende(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public String getGruppe() {
        return gruppe;
    }

    public long getUgetid() {
        return ugetid;
    }

    public boolean isGruppeleder() {
        return gruppeleder;
    }

    public List<String> getProjekter() {
        return projekter;
    }

    @Override
    public String toString() {
        return "Studerende{" +
                ", brugernavn='" + brugernavn + '\'' +
                ", gruppe='" + gruppe + '\'' +
                ", ugetid=" + ugetid +
                ", gruppeleder=" + gruppeleder +'\''+
                ",projekter='"+ projekter.toString() +
                '}';
    }
}
