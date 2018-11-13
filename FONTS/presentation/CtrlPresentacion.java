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
    private CtrlDomain CD = new CtrlDomain();
    /**Controlador de Presentacion que gestiona generación de Horarios.*/
    private CtrlPresentacionGenHorario CPgh;
    /**Controlador de Presentacion que gestiona las vistas de los menús.*/
    private VistaMantHorario Vmh;

    /**
     * Constructora de la clase CtrlPresentacion.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public CtrlPresentacion() throws FileNotFoundException, IOException, ParseException {}

    /**
     * Inicializa un CtrlPresentacion.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void initCtrlPresentacion() throws FileNotFoundException, IOException, ParseException {
        this.CD.initCtrlDomain();
        Vmh = new VistaMantHorario();
    }

    /**
     * Inicializa VistaMantHorario.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void initMantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        ArrayList<String> escenarios = this.CD.allEscenarios();
        CPgh = new CtrlPresentacionGenHorario(Vmh, escenarios, CD);
        CPgh.mantenimientoHorario();
    }

}
