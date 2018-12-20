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
 * Muestra un Horario.
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
public class showHorario {

    /**Instancia de sí mismo para singleton.*/
    private static showHorario sH;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;
    /**Horario a mostrar.*/
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    /**Constructoras*/

    /**Constructora de la clase showHorario.*/
    private showHorario() {
    }

    /**Instanciadora de la clase showHorario.*/
    public static showHorario getInstance() {
        if (sH == null) {
            sH = new showHorario() {
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

    /**
     * Función que muestra el Horario.
     * @param file  archivo del Horario que se muestra.
     */
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
                horario = cd.escaneaHorario(file, false);
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
