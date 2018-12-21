/**CtrlPresentation1a*/

/**Imports*/

package presentation;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import domaincontrollers.CtrlDomain;

/**
 * CtrlPresentación1a gestiona la interacción con Dominio y los diferentes controladores de la capa Presentación (1a entrega).
 * @author  Enric Sosa
 * @see     IOException
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     ArrayList
 * @see     CtrlDomain
 */
public class CtrlPresentacion1a {

    /**CtrlDomain del programa.*/
    private CtrlDomain CD;
    /**Controlador de Presentacion que gestiona generación de Horarios.*/
    private CtrlPresentacionGenHorario1a CPgh;
    /**Controlador de Presentacion que gestiona las vistas de los menús.*/
    private VistaMantHorario1a Vmh;

    /**
     * Constructora de la clase CtrlPresentacion1a.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public CtrlPresentacion1a() throws FileNotFoundException, IOException, ParseException {
        CD = CtrlDomain.getInstance();
    }

    /**
     * Inicializa un CtrlPresentacion.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public void initCtrlPresentacion() throws FileNotFoundException, IOException, ParseException {
        this.CD.initCtrlDomain();
        Vmh = new VistaMantHorario1a();
    }

    /**
     * Inicializa VistaMantHorario.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public void initMantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        ArrayList<String> escenarios = this.CD.allEscenarios();
        CPgh = new CtrlPresentacionGenHorario1a(Vmh, escenarios, CD);
        CPgh.mantenimientoHorario();
    }

}