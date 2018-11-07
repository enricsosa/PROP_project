package presentation;

import domaincontrollers.CtrlHorario;

import java.util.*;

public class CtrlPresentacionGenHorario {

    private VistaMantHorario Vmh;
    private CtrlHorario CDh;

    public CtrlPresentacionGenHorario(VistaMantHorario vmh, CtrlHorario cdh) {
        Vmh = vmh;
        CDh = cdh;
    }

    public void mantenimientoHorario() {
        int op = -1;
        Vmh.mostrarMenu("mainMenu");

        while (op != 0) {
            op = Vmh.obtenerOp(3);
            switch (op) {
                case 1:
                    generarHorarios();
                    break;

                case 2:
                    consultarHorarios();
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        }
    }

    public void generarHorarios() {

    }

    public void consultarHorarios() {

    }

}
