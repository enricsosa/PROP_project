package presentation.FXMLControllers;

import presentation.*;
import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class escogerHorario {

    private SceneController sc;
    private CtrlPresentacion cp;
    private CtrlDomain cd;
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    private final DataFormat buttonFormat = new DataFormat("hola");
    private Button draggingButton;
    private boolean editing = false;
    private boolean disSaveBtn = true;


    @FXML
    public Label horario_label;

    @FXML
    public Button saveButton;

    @FXML
    public Button backBtn;

    @FXML
    public VBox leftVB;

    public void escribirHorario(String idH) {
        try {
            cd.writeHorarioFromMap(horario, idH);
        } catch (Exception e) {
            System.out.println("ERROR EN LA ESCRITURA DEL HORARIO");
        }
    }


    public escogerHorario() {
        sc = SceneController.getInstance();
        cp = CtrlPresentacion.getInstance();
        cd = cp.getCD();
    }

    public void setMainMenu() throws Exception {
        sc.activate("prova1");
    }

    private Button draggableButton(String text) {
        Button button = new Button(text);
        button.setOnDragDetected(e -> {
            Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(button.snapshot(null, null));
            ClipboardContent cc = new ClipboardContent();
            cc.put(buttonFormat, "button");
            db.setContent(cc);
            draggingButton = button ;
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

                horario.get(iD).get(iH).remove(draggingButton.getText());
                horario.get(fD).get(fH).add(draggingButton.getText());

                ((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
                pane.getChildren().add(draggingButton);
                e.setDropCompleted(true);
            }
        });

        pane.setOnDragDone(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat) && !editing) {
                edit_new_horario edh = edit_new_horario.getInstance();
                edh.display();

                if (!edh.getEdit()) {
                    escribirHorario(edh.getNewName());
                    horario_label.setText(edh.getNewName());

                    BorderPane bP = (BorderPane)sc.getPane("escogerHorario");
                    VBox vb = (VBox)bP.getLeft();

                    Button b = new Button();
                    b.setText(edh.getNewName());
                    b.setId("esc-btn");
                    b.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(escogerHorario.class.getResource(""));
                        horarioSeleccionado(b.getText());
                    });

                    vb.getChildren().addAll(b);
                }

                editing = true;
                saveButton.setDisable(false);
                backBtn.setDisable(true);
                BorderPane bP = (BorderPane)sc.getPane("escogerHorario");
                VBox vb = (VBox)bP.getLeft();
                for (Node btn : vb.getChildren()) {
                    btn.setDisable(true);
                }
            }
        });
    }

    public void saveHorario() {
        try {
            cd.writeHorarioFromMap(horario, horario_label.getText());
        } catch (Exception e) {
            System.out.println("ERROR EN LA ESCRITURA DEL HORARIO");
        }
        editing = false;
        saveButton.setDisable(true);
        backBtn.setDisable(false);
        BorderPane bP = (BorderPane)sc.getPane("escogerHorario");
        VBox vb = (VBox)bP.getLeft();
        for (Node btn : vb.getChildren()) {
            btn.setDisable(false);
        }
    }

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
                sp.setHbarPolicy(ScrollBarPolicy.NEVER);
                gp.add(sp, dia.getKey(), hora.getKey() - 7);
                vB.setId(dia.getKey().toString() + ',' + hora.getKey().toString());
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
        saveButton.setDisable(true);
    }
}
