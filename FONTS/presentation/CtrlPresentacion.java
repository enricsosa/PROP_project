package presentation;

import java.io.IOException;

import domaincontrollers.CtrlHorario;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import domaincontrollers.CtrlDomain;

public class CtrlPresentacion {

    private CtrlDomain CD = new CtrlDomain();
    private CtrlHorario CDh;
    private CtrlPresentacionGenHorario CPgh;
    private VistaMantHorario Vmh;

    public CtrlPresentacion() throws FileNotFoundException, IOException, ParseException {}

    public void initCtrlPresentacion() throws FileNotFoundException, IOException, ParseException {
        this.CD.initCtrlDomain();
        CDh = CD.getCtrlHorario();
        Vmh = new VistaMantHorario();
    }

    public void initMantenimientoHorario() throws FileNotFoundException, IOException, ParseException {
        ArrayList<String> escenarios = this.CD.allEscenarios();
        CPgh = new CtrlPresentacionGenHorario(Vmh, CDh, escenarios, CD);
        CPgh.mantenimientoHorario();
    }

}
