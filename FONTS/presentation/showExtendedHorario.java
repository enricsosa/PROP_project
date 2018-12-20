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

/**
 * Muestra un Horario extendido.
 * @author  Enric Sosa
 * @see     Scene
 * @see     Parent
 * @see     Stage
 * @see     FXMLLoader
 * @see     ArrayList
 * @see     HashMap
 * @see     Map
 * @see     Button
 * @see     VBox
 */
public class showExtendedHorario {

    /**Instancia de sí mismo para singleton.*/
    private static showExtendedHorario sH;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;
    /**Horario a mostrar.*/
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    /**Constructoras*/

    /**Constructora de la clase showExtendedHorario.*/
    private showExtendedHorario() {
    }

    /**Instanciadora de la clase showExtendedHorario.*/
    public static showExtendedHorario getInstance() {
        if (sH == null) {
            sH = new showExtendedHorario() {
            };
        }
        return sH;
    }

    /**
     * Asigna el horario a mostrar.
     * @param gp    Grid donde se muestra el Horario.
     */
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

    /**
     * Función que muestra el Horario.
     * @param file  archivo del Horario que se muestra.
     */
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
