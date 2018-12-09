package presentation;

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
import java.util.Map;

public class escogerHorario {

    private SceneController sc;
    private CtrlPresentacion cp;
    private CtrlDomain cd;
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    @FXML
    public Label horario_label;

    public escogerHorario() {
        sc = SceneController.getInstance();
        cp = CtrlPresentacion.getInstance();
        cd = cp.getCD();
    }

    public void setMainMenu() throws Exception {
        sc.activate("prova1");
    }

    private void setHorario(GridPane gp) {
        for (Map.Entry<Integer, HashMap<Integer, ArrayList<String>>> dia : horario.entrySet()) {
            for (Map.Entry<Integer, ArrayList<String>> hora : dia.getValue().entrySet()) {
                ScrollPane sp = new ScrollPane();
                VBox vB = new VBox();
                for (String sesion : hora.getValue()) {
                    Button b = new Button(sesion);
                    b.setId("ses-btn");
                    vB.getChildren().add(b);
                }
                sp.setContent(vB);
                sp.setPannable(true);

                gp.add(sp, dia.getKey(), hora.getKey() - 7);
            }
        }
    }

    public void horarioSeleccionado(String s) {
        horario_label.setText(s);
        try {
            horario = cd.escaneaHorario(s);
        } catch (Exception e) {
            System.out.println("ERROR: CARGA DEL HORARIO FALLIDA");
        }
        BorderPane bP = (BorderPane)sc.getPane("escogerHorario");
        GridPane gP = (GridPane)bP.getCenter();
        setHorario(gP);
        bP.setCenter(gP);
    }

    @FXML
    private void initialize() throws Exception {

    }
}
