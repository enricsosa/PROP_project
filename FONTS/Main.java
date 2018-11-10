import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import presentation.CtrlPresentacion;

import data.*;
import domaincontrollers.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException  {
        System.out.println("Generador de horarios PROP 2017/18 Q2\n");


            CtrlPresentacion viz = new CtrlPresentacion();
            viz.initCtrlPresentacion();
            viz.initMantenimientoHorario();

        //CtrlDomain ctrl = new CtrlDomain();

        //CtrlEscenariosDir ctrl;
        //ctrl = CtrlEscenariosDir.getInstance();
        //ctrl.escanearAllEscenarios();
    }
}
