package presentation;

import domaincontrollers.CtrlDomain;
import domaincontrollers.CtrlHorario;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CtrlPresentacionGenHorario {

    private VistaMantHorario Vmh;
    private CtrlDomain CD;
    private ArrayList<String> Escenarios;
    private Integer idHorario = 1;
    private String horario;

    public CtrlPresentacionGenHorario(VistaMantHorario vmh, ArrayList<String> escenarios, CtrlDomain cd) {
        Vmh = vmh;
        Escenarios = escenarios;
        CD = cd;
    }

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

    private void mostrarHorario(boolean saveHorario) throws FileNotFoundException, IOException, ParseException {
        System.out.println(this.horario);
        if (saveHorario) {
            CD.writeHorario(this.horario, this.idHorario);
            ++idHorario;
        }
    }

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

}
