/**Main*/

/**Imports*/

import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

import presentation.CtrlPresentacion;
import presentation.TestFX;
import presentation.test2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;



//import data.*;
//import domaincontrollers.*;

/**
 * Main del programa.
 * @author  Enric Sosa
 * @see     IOException
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     CtrlPresentacion
 */
public class Main {

    /**
     * Método que ejecuta el programa.
     * @param args                      Argumentos del programa.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        /*
        System.out.println("Generador de horarios PROP 2017/18 Q2\n");
        CtrlPresentacion viz = new CtrlPresentacion();
        viz.initCtrlPresentacion();
        viz.initMantenimientoHorario();
        */

        /*
        System.out.println("Generador de horarios PROP 2017/18 Q2\n");
        CtrlPresentacion viz;
        viz = CtrlPresentacion.getInstance();
        viz.initCtrlPresentacion();

        Application.launch(TestFX.class, args);

        */
        System.out.println("Generador de horarios PROP 2017/18 Q2\n");
        CtrlPresentacion viz;
        viz = CtrlPresentacion.getInstance();
        viz.initCtrlPresentacion();

        Application.launch(test2.class, args);

        //CtrlDomain ctrl = new CtrlDomain();
        //CtrlEscenariosDir ctrl;
        //ctrl = CtrlEscenariosDir.getInstance();
        //ctrl.escanearAllEscenarios();
    }
}
