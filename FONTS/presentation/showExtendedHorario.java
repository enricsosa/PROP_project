package presentation;

import presentation.FXMLControllers.*;
import domaincontrollers.CtrlDomain;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class showExtendedHorario {

    private static showExtendedHorario sH;
    private CtrlDomain cd;
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    /**Constructoras*/

    private showExtendedHorario() {
    }

    public static showExtendedHorario getInstance() {
        if (sH == null) {
            sH = new showExtendedHorario() {
            };
        }
        return sH;
    }

    private void setHorario(GridPane gp) {
        for (Map.Entry<Integer, HashMap<Integer, ArrayList<String>>> dia : horario.entrySet()) {
            for (Map.Entry<Integer, ArrayList<String>> hora : dia.getValue().entrySet()) {
                ScrollPane sp = new ScrollPane();
                VBox vB = new VBox();
                vB.prefHeightProperty().bind(sp.heightProperty());
                vB.prefWidthProperty().bind(sp.widthProperty());
                for (String sesion : hora.getValue()) {
                    Button b = new Button(sesion);
                    b.setId("ses-btn");
                    vB.getChildren().add(b);
                }
                sp.setContent(vB);
                sp.setPannable(true);
                sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                gp.add(sp, dia.getKey(), hora.getKey()+1);
            }
        }
    }

    public void display(String file) {
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/showExtendedHorario.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            ScrollPane sP = (ScrollPane)root;
            sP.getStylesheets().add("presentation/CSS/escogerHorarios.css");

            try {
                horario = cd.escaneaHorario(file, true);
            } catch (Exception e) {
                System.out.println("ERROR: CARGA DEL HORARIO FALLIDA");
            }

            //Grid Pane
            GridPane gP = (GridPane)sP.getContent();
            gP.prefWidthProperty().bind(sP.widthProperty());
            setHorario(gP);

            window.setScene(new Scene(sP));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.toString());
        }
    }

}
