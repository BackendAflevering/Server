package logik;

import brugerautorisation.transport.soap.Brugeradmin;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;
import java.net.MalformedURLException;
import java.net.URL;

public class Login {

    public boolean tjekLogin(String brugernavn, String adgangskode) throws MalformedURLException {
        URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
        QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
        Service service = Service.create(url, qname);

        qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplPort");
        Brugeradmin ba = service.getPort(qname, Brugeradmin.class);
        try{
            System.out.println(ba.hentBruger(brugernavn, adgangskode));
            return true;
        } catch (SOAPFaultException e) {
            System.out.println("Forkert brugernavn eller kodeord");
            return false;
        }
    }
}
