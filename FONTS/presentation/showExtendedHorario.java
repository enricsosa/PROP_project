package presentation;

import javafx.collections.ObservableList;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
    /**buttonFormat*/
    private DataFormat buttonFormat = new DataFormat("ewfrgth");
    /**draggingButton*/
    private Button draggingButton;
    /**editing*/
    private boolean editing = false;
    /**nombre del Horario.*/
    private String nomH;

    private FXMLLoader loader;
    private Parent root;


    /**Constructoras*/

    /**Constructora de la clase showExtendedHorario.*/
    private showExtendedHorario() {
    }

    /**
     * Instanciadora de la clase showExtendedHorario.
     * @return  Instancia.
     */
    public static showExtendedHorario getInstance() {
        if (sH == null) {
            sH = new showExtendedHorario() {
            };
        }
        return sH;
    }

    /**
     * Comprueba o comprueba y realiza un moviemto de asignación.
     * @param buttonText    texto del objeto que se mueve.
     * @param iD            dia inicial
     * @param iH            hora inicial
     * @param fD            dia final
     * @param fH            horaIni final
     * @param onlyCheck     bollean que indica si se realiza movimiento.
     * @return              si se puede realizar el movimiento o no.
     */
    private boolean checkChange(String buttonText, int iD, int iH, int fD, int fH, boolean onlyCheck) {
        int firstSpace = buttonText.indexOf(" ");
        int secondSpace = buttonText.indexOf(" ", firstSpace+1);
        int thirdSpace = buttonText.indexOf(" ", secondSpace+1);
        String idAsignatura = buttonText.substring(0, buttonText.indexOf(" "));
        String idsubG = buttonText.substring(secondSpace+1, thirdSpace);

        if (onlyCheck)
            return (cd.checkMoverAsignacion(idAsignatura, idsubG, iD, iH, fD, fH));
        else
            return (cd.moverAsignacion(idAsignatura, idsubG, iD, iH, fD, fH) >= 0);
    }

    private void checkOptions(GridPane gp, String buttonText, int iD, int iH) {
        ObservableList ol = gp.getChildren();
        for (Object n : ol) {
            if (n.getClass().equals(ScrollPane.class)) {
                ScrollPane sp = (ScrollPane)n;
                String destVBox = sp.getId();
                Integer fD = Integer.parseInt(destVBox.substring(0, 1));
                Integer fH = Integer.parseInt(destVBox.substring(destVBox.indexOf(',')+1));

                if (checkChange(buttonText, iD, iH, fD, fH, true))
                    sp.setStyle("-fx-border-color: #42f442");
                else
                    sp.setStyle("-fx-border-color: #f9bbb3");
            }
        }
    }

    private Button draggableButton(String text) {
        Button button = new Button(text);
        button.setOnDragDetected(e -> {
            Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(button.snapshot(null, null));
            ClipboardContent cc = new ClipboardContent();
            cc.put(buttonFormat, "button");
            db.setContent(cc);
            draggingButton = button;

            ScrollPane sP = (ScrollPane)root;
            GridPane gP = (GridPane)sP.getContent();
            String initVBox = ((Pane)draggingButton.getParent()).getId();
            Integer iD = Integer.parseInt(initVBox.substring(0, 1));
            Integer iH = Integer.parseInt(initVBox.substring(initVBox.indexOf(',')+1));

            checkOptions(gP, draggingButton.getText(), iD, iH);

        });
        button.setOnDragDone(e -> draggingButton = null);
        return button ;
    }

    private void addDropHandling(Pane pane) {
        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)
                    && draggingButton != null
                    && draggingButton.getParent() != pane) {
                e.acceptTransferModes(TransferMode.MOVE);

            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)) {
                String initVBox = ((Pane)draggingButton.getParent()).getId();
                String destVBox = pane.getId();
                Integer iD, iH, fD, fH;
                iD = Integer.parseInt(initVBox.substring(0, 1));
                iH = Integer.parseInt(initVBox.substring(initVBox.indexOf(',')+1));
                fD = Integer.parseInt(destVBox.substring(0, 1));
                fH = Integer.parseInt(destVBox.substring(destVBox.indexOf(',')+1));
                boolean aux = checkChange(draggingButton.getText(), iD, iH, fD, fH, false);
                System.out.println(aux);
                if (aux) {
                    try {
                        cd.writeHorario(cd.getHorarioActivo().toString(), nomH);
                        horario = cd.escaneaHorario(nomH, true);
                    } catch (Exception ee) {
                        System.out.println("ERROR");
                    }

                    ScrollPane sP = (ScrollPane)root;
                    GridPane gP = (GridPane)sP.getContent();
                    setHorario(gP);
                }
                e.setDropCompleted(true);
            }
        });
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
                    Button b = draggableButton(sesion);
                    b.setId("ses-btn");
                    vB.getChildren().add(b);
                }
                addDropHandling(vB);
                sp.setContent(vB);
                sp.setPannable(true);
                sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                gp.add(sp, dia.getKey(), hora.getKey()+1);
                vB.setId(dia.getKey().toString() + ',' + hora.getKey().toString());
                sp.setId(dia.getKey().toString() + ',' + hora.getKey().toString());
            }
        }
    }

    /**
     * Función que muestra el Horario.
     * @param file  archivo del Horario que se muestra.
     */
    public void display(String file) {
        nomH = file;
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();

        loader = new FXMLLoader(getClass().getResource("FXML/showExtendedHorario.fxml"));
        root = null;
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
