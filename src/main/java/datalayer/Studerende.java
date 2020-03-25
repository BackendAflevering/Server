package datalayer;

public class Studerende {
    private static final long serialVersionUID = 12345;

    private String brugernavn;
    private String gruppe;
    private long ugetid;
    private long projekttid;
    private String mail;
    private boolean gruppeleder;

    public Studerende(String brugernavn, String gruppe, long ugetid, long projekttid, String mail, boolean gruppeleder){
        this.brugernavn = brugernavn;
        this.gruppe = gruppe;
        this.ugetid = ugetid;
        this.projekttid = projekttid;
        this.gruppeleder = gruppeleder;
        this.mail = mail;
    }

    public String toString() {return brugernavn+"/"+gruppe+"/"+mail+"/ tid brugt denne uge:"+ugetid+"/ tid brugt dette projekt:"+projekttid;}
}
