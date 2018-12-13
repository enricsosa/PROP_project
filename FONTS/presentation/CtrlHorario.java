package presentation;

import presentation.FXMLControllers.*;
import domaincontrollers.CtrlDomain;
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

public class CtrlHorario {

    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();
    private String horarioS;

    public CtrlHorario(String horarioS) {
        this.horarioS = horarioS;
    }

    public HashMap<Integer, ArrayList<String>> getDia(int dia) {
        return horario.get(dia);
    }

    public ArrayList<String> getHora(int dia, int hora) {
        return horario.get(dia).get(hora);
    }

    public String getSesio(int dia, int hora, String sesion) {
        ArrayList<String> h = horario.get(dia).get(hora);
        return h.get(h.indexOf(sesion));
    }

    public void addSesio(int dia, int hora, String sesion) {
        horario.get(dia).get(hora).add(sesion);
    }

    private HashMap<Integer, ArrayList<String>> initHorasDia() {
        HashMap<Integer, ArrayList<String>> dia = new HashMap<>();

        for (int i = 8; i < 20; ++i) {
            ArrayList<String> hora = new ArrayList<>();
            dia.put(i, hora);
        }

        return dia;
    }

    private void initHorario() {
        for (int i = 1; i < 5; ++i)
            horario.put(i, initHorasDia());
    }

    private void processHorario() {
        String aux = horarioS;
        
    }

    @FXML
    private void initialize() throws Exception {
        initHorario();

    }
}
