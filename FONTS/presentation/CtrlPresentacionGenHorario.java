/**CtrlPresentacionGenHorario*/

/**Imports*/

package presentation;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;

import domaincontrollers.CtrlDomain;
//import domaincontrollers.CtrlHorario;

/**
 * Controlador de Presentacion que gestiona la generación de Horarios.
 * @author  Enric Sosa
 * @see     FileNotFoundException
 * @see     IOException
 * @see     ArrayList
 * @see     ParseException
 * @see     CtrlDomain
 */
public class CtrlPresentacionGenHorario {

    /**Atributos*/

    /**VistaMantHorario del programa.*/
    private VistaMantHorario Vmh;
    /**CtrlDomain del programa.*/
    private CtrlDomain CD;
    /**Escenarios almacenados.*/
    private ArrayList<String> Escenarios;
    /**id del siguiente horario a generar.*/
    private Integer idHorario = 1;
    /**String que contiene información de un Horario.*/
    private String horario;

    /**Constructoras*/

    /**
     * Creadora de la clase CtrlPresentacionGenHorario.
     * @param vmh           VistaMantHorario que se asigna a CtrlPresentacionGenHorario.
     * @param escenarios    Listado de escenarios que se asigna a CtrlPresentacionGenHorario.
     * @param cd            CtrlDomain que se asigna a CtrlPresentacionGenHorario.
     */
    public CtrlPresentacionGenHorario(VistaMantHorario vmh, ArrayList<String> escenarios, CtrlDomain cd) {
        Vmh = vmh;
        Escenarios = escenarios;
        CD = cd;
    }

    /**Métodos públicos*/

    /**
     * Gestiona el menú de Horarios.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void mantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        int op = -1;
        boolean br = false;
        Vmh.mostrarMenu("mainMenu");

        while (op != 0) {
            op = Vmh.obtenerOp(3);
            switch (op) {
                case 1:
                    escogerEscenarios();
                    break;

                case 2:
                    consultarHorarios();
                    break;

                case 3:
                    op = 0;
                    br = true;
                    break;

                default:
                    op = 0;
                    br = true;
                    break;
            }
            if (op != 0) {
                Vmh.mostrarMenu("mainMenu");
            }
        }
    }

    /**
     * Gestiona el menú de selección de escenarios.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private void escogerEscenarios() throws FileNotFoundException, IOException, ParseException {
        int op = -1;
        Vmh.mostrarMenuDinamico(Escenarios, "escenario");

        while (op != 0) {
            op = Vmh.obtenerOp(Escenarios.size()+1);
            if (op == Escenarios.size()+1)
                op = 0;
            else {
                System.out.print("Cargando datos...");
                CD.cargarEscenario(Escenarios.get(op-1));
                System.out.print("\nDatos cargados\n");
                System.out.print("Generando horario...\n");
                this.horario = CD.generarHorario("Horario#" + idHorario.toString());
                if (this.horario.equals("false")) {
                    System.out.print("No se ha podido generar un horario.\n\n");
                    System.out.print("Vuelve a escoger otro escenario.");
                    op = -1;
                } else {
                    escogerGenHorario();
                }
            }
            if (op != 0) {
                Vmh.mostrarMenuDinamico(Escenarios, "escenario");
            }
        }
    }

    /**
     * Gestiona el menú de generación de Horarios.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private void escogerGenHorario() throws FileNotFoundException, IOException, ParseException {
        int op = -1;
        Vmh.mostrarMenu("escGenHor");

        while (op != 0) {
            op = Vmh.obtenerOp(3);
            switch (op) {
                case 1:
                    mostrarHorario(true);
                    op = 0; //Salir
                    break;

                case 2:
                    mostrarHorario(false);
                    op = 0; //Salir
                    break;

                case 3:
                    op = 0; //Salir
                    break;

                default:
                    op = 0; //Salir
                    break;
            }
        }
    }

    /**
     * Muestra un Horario por consola.
     * @param saveHorario
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private void mostrarHorario(boolean saveHorario) throws FileNotFoundException, IOException, ParseException {
        System.out.println(this.horario);
        if (saveHorario) {
            CD.writeHorario(this.horario, this.idHorario.toString());
            ++idHorario;
        }
    }

    /**
     * Consulta un Horario.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void consultarHorarios() throws FileNotFoundException, IOException {
        int op = -1;
        ArrayList<String> horarios = new ArrayList<String>();
        horarios = CD.allHorarios();
        Vmh.mostrarMenuDinamico(horarios, "escenario");
        while (op != 0) {
            op = Vmh.obtenerOp(horarios.size()+1);
            if (op == horarios.size()+1)
                op = 0;
            else {
                System.out.println(CD.readHorario(horarios.get(op-1)));
            }
            if (op != 0) {
                Vmh.mostrarMenuDinamico(horarios, "escenario");
            }
        }
    }

    /**Consultoras*/

    /**Métodos redefinidos*/

}
