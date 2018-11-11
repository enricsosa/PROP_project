package presentation;

import domaincontrollers.CtrlDomain;
import domaincontrollers.CtrlHorario;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CtrlPresentacionGenHorario {

    private VistaMantHorario Vmh;
    private CtrlDomain CD;
    private ArrayList<String> Escenarios;
    private Integer idHorario = 0;

    public CtrlPresentacionGenHorario(VistaMantHorario vmh, ArrayList<String> escenarios, CtrlDomain cd) {
        Vmh = vmh;
        Escenarios = escenarios;
        CD = cd;
    }

    public void mantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        int op = -1;
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
                    break;

                default:
                    op = 0;
                    break;
            }
        }
    }

    private void escogerEscenarios() throws FileNotFoundException, IOException, ParseException {
        System.out.println("caca");
        int op = -1;
        Vmh.mostrarMenuEscenarios(Escenarios);

        while (op != 0) {
            op = Vmh.obtenerOp(Escenarios.size()+1);
            if (op == Escenarios.size()+1)
                this.mantenimientoHorario();
            else {
                System.out.print("Cargando datos...");
                CD.cargarEscenario(Escenarios.get(op-1));
                System.out.print("\nDatos cargados\n");
                System.out.print("Generando horario...");
                CD.generarHorario("Horario#" + idHorario.toString());

                ++idHorario;
            }
        }
    }

    private void consultarHorarios() {



    }

}
