/**CtrlHorario*/

/**Imports*/

package presentation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.HashMap;

import presentation.FXMLControllers.*;
import domaincontrollers.CtrlDomain;

/**
 * CtrlHorario
 * @author  Enric Sosa
 * @see     FXML
 * @see     ArrayList
 * @see     HashMap
 */
public class CtrlHorario {

    /**Horario que se gestiona.*/
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();
    /**horario en forma de String.*/
    private String horarioS;

    /**
     * Constructora de la clase CtrlHorario.
     * @param horarioS  horario en forma de String.
     */
    public CtrlHorario(String horarioS) {
        this.horarioS = horarioS;
    }

    /**
     * Obtiene un dia de horario.
     * @param dia   dia que se quiere obtener.
     * @return      dia de horario.
     */
    public HashMap<Integer, ArrayList<String>> getDia(int dia) {
        return horario.get(dia);
    }

    /**
     * Obtiene una hora de horario.
     * @param dia   dia de la hora que se quiere obtener.
     * @param hora  hora que se quiere obtener.
     * @return      hora de horario.
     */
    public ArrayList<String> getHora(int dia, int hora) {
        return horario.get(dia).get(hora);
    }

    /**
     * Obtiene una Sesion de horario.
     * @param dia       Dia de la Hora de la Sesion que se quiere obtener.
     * @param hora      Hora de la Sesion que se quiere obtener.
     * @param sesion    Sesion que se quiere obtener.
     * @return          Sesion en forma de String.
     */
    public String getSesio(int dia, int hora, String sesion) {
        ArrayList<String> h = horario.get(dia).get(hora);
        return h.get(h.indexOf(sesion));
    }

    /**
     * Añade una Sesion a horario.
     * @param dia       Dia de la Hora donde se quiere añadir sesion.
     * @param hora      Hora donde se quiere añadir sesion.
     * @param sesion    Sesion que se quiere añadir.
     */
    public void addSesio(int dia, int hora, String sesion) {
        horario.get(dia).get(hora).add(sesion);
    }

    /**
     * Inicializa las Horas de un Dia.
     * @return  Horas del dia inicializadas.
     */
    private HashMap<Integer, ArrayList<String>> initHorasDia() {
        HashMap<Integer, ArrayList<String>> dia = new HashMap<>();

        for (int i = 8; i < 20; ++i) {
            ArrayList<String> hora = new ArrayList<>();
            dia.put(i, hora);
        }

        return dia;
    }

    /** Inicializa horario.*/
    private void initHorario() {
        for (int i = 1; i < 5; ++i)
            horario.put(i, initHorasDia());
    }

    /**Procesa horario.*/
    private void processHorario() {
        String aux = horarioS;
        
    }

    @FXML
    private void initialize() throws Exception {
        initHorario();

    }
}
