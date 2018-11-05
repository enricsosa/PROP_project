package presentation;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import domaincontrollers.CtrlDomain;

public class CtrlPresentacion {

    private CtrlDomain controladorDominio = new CtrlDomain();

    public CtrlPresentacion() throws FileNotFoundException, IOException, ParseException {}

    public void initCtrlPresentacion() throws FileNotFoundException, IOException, ParseException {
        System.out.println("Hello World!");
        this.controladorDominio.initCtrlDomain();
    }


}
