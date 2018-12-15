/**CtrlPresentation*/

/**Imports*/

package presentation;

import presentation.FXMLControllers.*;
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

    private static CtrlPresentacion CP;

    /**Constructoras*/

    /**Constructora de la clase CtrlPresentacion.*/
    private CtrlPresentacion() {
    }

    /**
     * Instanciadora de CtrlPresentacion.
     * @return  instancia de CtrlPresentacion.
     */
    public static CtrlPresentacion getInstance() {
        if (CP == null)
            CP = new CtrlPresentacion() {
            };
        return CP;
    }

    /**
     * Inicializa un CtrlPresentacion.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public void initCtrlPresentacion() throws FileNotFoundException, IOException, ParseException {
        CD = new CtrlDomain();
        //CD.initCtrlDomain();
        //Vmh = new VistaMantHorario();
    }

    /**
     * Inicializa VistaMantHorario.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public void initMantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        ArrayList<String> escenarios = this.CD.allEscenarios();
    }

    /**
     * Devuelve los escenarios disponibles.
     * @return  Escenarios disponibles.
     */
    public ArrayList<String> escenarios() {
        return CD.allEscenarios();
    }

    /**
     * Devuelve los horarios disponibles.
     * @return  Horarios disponibles.
     */
    public ArrayList<String> horarios() {
        return CD.allHorarios();
    }

    /**
     * Devuelve CrtlDomain.
     * @return  CtrlDomain.
     */
    public CtrlDomain getCD() {
        return CD;
    }

}
