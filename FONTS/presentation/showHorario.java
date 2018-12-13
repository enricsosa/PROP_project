package presentation;

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

    public void display(String file) {
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/showHorario2.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            BorderPane bP = (BorderPane)root;
            bP.getStylesheets().add("presentation/CSS/escogerHorarios.css");

            try {
                horario = cd.escaneaHorario(file);
            } catch (Exception e) {
                System.out.println("ERROR: CARGA DEL HORARIO FALLIDA");
            }

            //Label titol
            Label nomHor = (Label)bP.getTop();
            nomHor.setText(file);

            //Grid Pane
            GridPane gP = (GridPane)bP.getCenter();
            setHorario(gP);

            //AcceptButton
            HBox hB = (HBox)bP.getBottom();
            Button acceptB = (Button)hB.lookup("#acceptB");
            acceptB.setOnAction(event -> {
                window.close();
            });

            window.setScene(new Scene(bP));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.toString());
        }
    }

}
