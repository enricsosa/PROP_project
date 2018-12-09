package presentation;

import domain.Correquisito;
import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class showHorario {

    private static showHorario sH;
    private CtrlDomain cd;
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    /**Constructoras*/

    private showHorario() {
    }

    public static showHorario getInstance() {
        if (sH == null) {
            sH = new showHorario() {
            };
        }
        return sH;
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

    public void display(String s) {
        cd = CtrlPresentacion.getInstance().getCD();

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showHorario.fxml"));
        //cR = loader.getController();
        Parent root = null;
        try {
            root = loader.load();
            VBox vb = (VBox)root;

            try {
                horario = cd.escaneaHorario(s);
            } catch (Exception e) {
                System.out.println("ERROR: CARGA DEL HORARIO FALLIDA");
            }

            //Label titol
            Label nomHor = (Label)vb.lookup("#titulo");
            nomHor.setText(s);

            //Grid Pane
            GridPane gP = (GridPane) vb.lookup("#gridPane");
            setHorario(gP);

            window.setScene(new Scene(vb));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

}
