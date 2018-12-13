/**CtrlPresentation*/

/**Imports*/

package presentation;

import java.io.IOException;
//import domaincontrollers.CtrlHorario;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import domaincontrollers.CtrlDomain;

/**
 * CtrlPresentación gestiona la interacción con Dominio y los diferentes controladores de la capa Presentación.
 * @author  Enric Sosa
 * @see     IOException
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     ArrayList
 * @see     CtrlDomain
 */
public class CtrlPresentacion {

    /**CtrlDomain del programa.*/
    private CtrlDomain CD;

    private ArrayList<String> Escenarios;

    /**
     * Constructora de la clase CtrlPresentacion.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private static CtrlPresentacion CP;

    /**Constructoras*/

    private CtrlPresentacion() {
    }

    public static CtrlPresentacion getInstance() {
        if (CP == null)
            CP = new CtrlPresentacion() {
            };
        return CP;
    }

    /**
     * Inicializa un CtrlPresentacion.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void initCtrlPresentacion() throws FileNotFoundException, IOException, ParseException {
        CD = new CtrlDomain();
        //CD.initCtrlDomain();
        //Vmh = new VistaMantHorario();
    }

    /**
     * Inicializa VistaMantHorario.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void initMantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        ArrayList<String> escenarios = this.CD.allEscenarios();
    }

    public ArrayList<String> escenarios() {
        return CD.allEscenarios();
    }

    public ArrayList<String> horarios() {
        return CD.allHorarios();
    }

    public CtrlDomain getCD() {
        return CD;
    }

}
