package datalayer;

public class Studerende {
    private static final long serialVersionUID = 12345;

    private int studerendeID;
    private String brugernavn;
    private String gruppe;
    private long ugetid;
    private long projekttid;
    private String mail;
    private boolean gruppeleder;

    public Studerende(int studerendeID, String brugernavn, String gruppe, long ugetid, long projekttid, String mail, boolean gruppeleder){
        this.studerendeID = studerendeID;
        this.brugernavn = brugernavn;
        this.gruppe = gruppe;
        this.ugetid = ugetid;
        this.projekttid = projekttid;
        this.gruppeleder = gruppeleder;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Studerende{" +
                "studerendeID=" + studerendeID +
                ", brugernavn='" + brugernavn + '\'' +
                ", gruppe='" + gruppe + '\'' +
                ", ugetid=" + ugetid +
                ", projekttid=" + projekttid +
                ", mail='" + mail + '\'' +
                ", gruppeleder=" + gruppeleder +
                '}';
    }
}
