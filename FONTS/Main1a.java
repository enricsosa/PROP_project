/**Main1a*/

/**Imports*/

import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import presentation.CtrlPresentacion1a;

/**
 * Main del programa (1a entrega).
 * @author  Enric Sosa
 * @see     IOException
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     CtrlPresentacion1a
 */
public class Main1a {

    /**
     * Método que ejecuta el programa de la 1a entrega.
     * @param args                      Argumentos del programa.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException  {
        System.out.println("Generador de horarios PROP 2017/18 Q1\n");
        CtrlPresentacion1a viz = new CtrlPresentacion1a();
        viz.initCtrlPresentacion();
        viz.initMantenimientoHorario();

        //CtrlDomain ctrl = new CtrlDomain();
        //CtrlEscenariosDir ctrl;
        //ctrl = CtrlEscenariosDir.getInstance();
        //ctrl.escanearAllEscenarios();
    }
}